package com.realworld.wages.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PurchaseReceiveDto {

    private Long receivedId;

    @NotNull(message = "poId is required")
    private Long poId;

    @NotNull(message = "supplierId is required")
    private Long supplierId;

    @NotBlank(message = "status is required")
    @Size(max = 100, message = "status must be at most 100 characters")
    private String status;

    @NotNull(message = "totalQuantity is required")
    @Min(value = 0, message = "totalQuantity must be >= 0")
    private Long totalQuantity;

    @NotNull(message = "updatedTotalAmount is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "updatedTotalAmount must be >= 0")
    private BigDecimal updatedTotalAmount;

    // line items: allow empty list but not null
    @NotNull(message = "lineItems must be provided (can be empty)")
    private List<PurchaseReceiveLineItemDto> lineItems;

    private Date createdDate;

}
