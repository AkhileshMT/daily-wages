package com.realworld.wages.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realworld.wages.dto.PurchaseReceiveDto;
import com.realworld.wages.dto.PurchaseReceiveLineItemDto;
import com.realworld.wages.entities.PurchaseReceive;
import com.realworld.wages.entities.PurchaseReceiveLineItem;
import com.realworld.wages.mapper.PurchaseReceiveLineItemMapper;
import com.realworld.wages.mapper.PurchaseReceiveMapper;
import com.realworld.wages.repository.PurchaseReceiveLineItemRepository;
import com.realworld.wages.repository.PurchaseReceiveRepository;
import com.realworld.wages.serviceIF.IPurchaseReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class PurchaseReceiveService implements IPurchaseReceiveService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PurchaseReceiveLineItemRepository purchaseReceiveLineItemRepository;

    @Autowired
    private PurchaseReceiveRepository purchaseReceiveRepository;

    @Override
    public PurchaseReceiveDto createPurchaseReceive(PurchaseReceiveDto purchaseReceiveDto, Long storeId, Long poId) {

        String response = validateId(poId, storeId);
        if (response == null || response.isBlank()) {
            throw new IllegalArgumentException("Empty PO validation response");
        }

        JsonNode root;
        try {
            root = objectMapper.readTree(response);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to parse PO validation response JSON", ex);
        }

        JsonNode data = root.path("data");
        if (data.isMissingNode() || data.isNull()) {
            throw new IllegalArgumentException("PO response missing 'data' node");
        }

        JsonNode poIdNode = data.path("po_id");
        if (poIdNode.isMissingNode() || !poIdNode.isNumber()) {
            throw new IllegalArgumentException("PO response missing numeric po_id");
        }
        long respPoId = poIdNode.asLong();
        if (!Objects.equals(respPoId, poId)) {
            throw new IllegalArgumentException("poId mismatch with PO response (expected " + poId + " got " + respPoId + ")");
        }

        JsonNode storeIdNode = data.path("store_id");
        if (!storeIdNode.isMissingNode() && storeIdNode.isNumber()) {
            long respStoreId = storeIdNode.asLong();
            if (!Objects.equals(respStoreId, storeId)) {
                throw new IllegalArgumentException("storeId mismatch with PO response (expected " + storeId + " got " + respStoreId + ")");
            }
        }

        Map<Long, Long> availableQtyByPoln = new HashMap<>();
        JsonNode poLineItems = data.path("po_line_items");
        if (poLineItems.isArray()) {
            for (JsonNode item : poLineItems) {
                JsonNode polnIdNode = item.path("poln_id");
                if (polnIdNode.isMissingNode() || !polnIdNode.canConvertToLong()) continue;
                Long polnId = polnIdNode.asLong();

                Long qty = null;
                JsonNode updQty = item.path("updated_line_quantity");
                if (!updQty.isMissingNode() && updQty.canConvertToLong()) qty = updQty.asLong();
                if (qty == null) {
                    JsonNode lineQty = item.path("line_quantity");
                    if (!lineQty.isMissingNode() && lineQty.canConvertToLong()) qty = lineQty.asLong();
                }
                if (qty == null) qty = 0L;
                availableQtyByPoln.put(polnId, qty);
            }
        }

        List<PurchaseReceiveLineItemDto> incomingLines = purchaseReceiveDto.getLineItems();
        if (incomingLines == null) {
            throw new IllegalArgumentException("lineItems must be provided (can be empty list)");
        }

        // Find existing OPEN PurchaseReceive for this PO (reuse it) or create new one
        PurchaseReceive receive;
        Optional<PurchaseReceive> existingOpt = purchaseReceiveRepository.findFirstByPoIdAndStatus(poId, "OPEN");
        boolean isNewParent = false;
        if (existingOpt.isPresent()) {
            receive = existingOpt.get();
        } else {
            receive = new PurchaseReceive();
            receive.setPoId(poId);
            receive.setSupplierId(purchaseReceiveDto.getSupplierId());
            receive.setStatus("OPEN");
            receive.setUpdatedTotalAmount(BigDecimal.ZERO);
            receive.setTotalQuantity(0L);
            isNewParent = true;
        }

        // Prepare to accumulate new totals to add to parent
        BigDecimal addTotalAmount = BigDecimal.ZERO;
        long addTotalQuantity = 0L;

        // For each incoming line, validate and create a new PurchaseReceiveLineItem entity (always new)
        List<PurchaseReceiveLineItem> newLineEntities = new ArrayList<>();
        for (PurchaseReceiveLineItemDto lineDto : incomingLines) {
            if (lineDto == null) throw new IllegalArgumentException("lineItems contains null element");

            Long poLineId = lineDto.getPoLineId();
            if (poLineId == null) throw new IllegalArgumentException("poLineId is required in each line");

            if (!availableQtyByPoln.containsKey(poLineId)) {
                throw new IllegalArgumentException("po_line_id " + poLineId + " not present in the PO response");
            }

            Long allowedQty = availableQtyByPoln.get(poLineId);

            long incomingReceived = lineDto.getReceived() == null ? 0L : lineDto.getReceived();
            if (incomingReceived < 0) {
                throw new IllegalArgumentException("received must be >= 0 for po_line_id " + poLineId);
            }

            // cumulative check (already persisted across DB). This includes existing `receive`'s children because they are persisted.
            Long alreadyReceived = 0L;
            try {
                Long r = purchaseReceiveLineItemRepository.sumReceivedByPoLineId(poLineId);
                if (r != null) alreadyReceived = r;
            } catch (Exception ex) {
                // if repository method not present, assume 0 (or add the repo method)
            }

            if (alreadyReceived + incomingReceived > allowedQty) {
                throw new IllegalArgumentException(String.format(
                        "Receiving %d for po_line_id %d would exceed allowed %d (already received %d)",
                        incomingReceived, poLineId, allowedQty, alreadyReceived));
            }

            // Create new line entity (use mapper but pass parent later)
            PurchaseReceiveLineItem lineEntity = PurchaseReceiveLineItemMapper.entity(lineDto, null);

            // Ensure ordered is set
            Long ordered = lineDto.getOrdered() != null ? lineDto.getOrdered() : allowedQty;
            lineEntity.setOrdered(ordered);

            lineEntity.setReceived(incomingReceived);

            // compute remaining relative to total previously received + this incoming: ordered - (alreadyReceived + incomingReceived)
            long remaining = Math.max(0L, ordered - (alreadyReceived + incomingReceived));
            lineEntity.setRemainingOrder(remaining);

            BigDecimal costPrice = lineDto.getCostPrice() != null ? lineDto.getCostPrice() : BigDecimal.ZERO;
            lineEntity.setCostPrice(costPrice);

            BigDecimal lineTotal = lineDto.getLineTotal() != null
                    ? lineDto.getLineTotal()
                    : costPrice.multiply(BigDecimal.valueOf(incomingReceived));
            lineEntity.setLineTotal(lineTotal);

            // Link to parent (if parent is new, we'll save parent which cascades children; if parent exists, adding child and saving parent will persist new child)
            lineEntity.setPurchaseReceive(receive);
            newLineEntities.add(lineEntity);

            // accumulate increments for parent
            addTotalAmount = addTotalAmount.add(lineTotal);
            addTotalQuantity += incomingReceived;
        }

        // Attach new line items to parent (append)
        if (receive.getLineItems() == null) {
            receive.setLineItems(new ArrayList<>());
        }
        receive.getLineItems().addAll(newLineEntities);

        // Update parent totals by adding increments
        BigDecimal prevTotal = receive.getUpdatedTotalAmount() == null ? BigDecimal.ZERO : receive.getUpdatedTotalAmount();
        receive.setUpdatedTotalAmount(prevTotal.add(addTotalAmount));

        long prevQty = receive.getTotalQuantity() == null ? 0L : receive.getTotalQuantity();
        receive.setTotalQuantity(prevQty + addTotalQuantity);

        // Save parent (cascade = ALL handles children)
        PurchaseReceive saved = purchaseReceiveRepository.save(receive);

        // convert to dto
        PurchaseReceiveDto savedDto = PurchaseReceiveMapper.dto(saved);
        if (savedDto.getLineItems() != null) {
            for (PurchaseReceiveLineItemDto l : savedDto.getLineItems()) {
                l.setReceivedId(saved.getReceivedId());
            }


            
        }

        return savedDto;
    }

    public static String validateId(Long poId, Long storeId) {

        String apiUrl = String.format(
                "https://apiv2.valmee.com:8443/valmee-dev/v1/pos/GET/0/%d/%d", storeId, poId
        );

        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhdXRoMCJ9.vp_1UxGVYv6KTpkDwWil6lvnl9gQNbhvZXTJ3l74omMj5HEWpDAnlwCxowWV-5Ip3U1qu3NR1iSqTuNmtBPVXg";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            // Set headers
            conn.setRequestProperty("Authorization", " " + authToken);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);

            BufferedReader in;
            if (responseCode >= 400) {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            System.out.println("API Response: " + response);

            if (responseCode == 200 && response.toString().contains(poId.toString())) {
                System.out.println("PO ID is valid: " + poId);
            } else if (responseCode == 200) {
                System.out.println("PO ID not found in API response");
            } else if (responseCode == 401) {
                System.out.println("Unauthorized: Invalid or expired token");
            } else if (responseCode == 404) {
                System.out.println("PO or Store not found");
            } else if (responseCode >= 500) {
                System.out.println("Server error. Please contact API provider.");
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception occurred: " + e.getMessage();
        }

    }

}
