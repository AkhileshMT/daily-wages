package com.realworld.wages.controller;

import com.realworld.wages.dto.PurchaseReceiveDto;
import com.realworld.wages.entities.PurchaseReceive;
import com.realworld.wages.mapper.PurchaseReceiveLineItemMapper;
import com.realworld.wages.mapper.PurchaseReceiveMapper;
import com.realworld.wages.service.PurchaseReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/receiver")
public class PurchaseReceiverResource {

    /**
     * Author Akhilesh
     */

    @Autowired
    private PurchaseReceiveService purchaseService;

    @PostMapping(value = "/create/{storeId}/{poId}",  produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<PurchaseReceiveDto> create(@PathVariable Long storeId, @PathVariable Long poId, @Validated @RequestBody PurchaseReceiveDto poReceiveDto) throws ParseException {

        poReceiveDto.setPoId(poId);

        PurchaseReceiveDto savedDto = purchaseService.createPurchaseReceive(poReceiveDto,storeId,poId);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }
}
