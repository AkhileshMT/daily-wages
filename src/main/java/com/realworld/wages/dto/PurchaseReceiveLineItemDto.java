package com.realworld.wages.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class PurchaseReceiveLineItemDto {

    private Long receivedLineItemId;

    @NotNull(message = "poLineId is required")
    private Long poLineId;

    @NotNull(message = "ordered is required")
    @Min(value = 0, message = "ordered must be >= 0")
    private Long ordered;

    @NotNull(message = "received is required")
    @Min(value = 0, message = "received must be >= 0")
    private Long received;

    @NotNull(message = "remainingOrder is required")
    @Min(value = 0, message = "remainingOrder must be >= 0")
    private Long remainingOrder;

    @NotNull(message = "costPrice is required")
    @DecimalMin(value = "0.00", message = "costPrice must be >= 0")
    private BigDecimal costPrice;

    @NotNull(message = "lineTotal is required")
    @DecimalMin(value = "0.00", message = "lineTotal must be >= 0")
    private BigDecimal lineTotal;

    private Date createdDate;

    private Date modifiedDate;

    private Long receivedId;
}
