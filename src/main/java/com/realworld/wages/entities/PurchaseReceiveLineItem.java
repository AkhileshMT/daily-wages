package com.realworld.wages.entities;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Hidden
@Table(name = "purchase_receive_line_item")
public class PurchaseReceiveLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "received_line_item_id",nullable = false, updatable = false)
    private Long receivedLineItemId;

    @Column(name = "po_line_id", nullable = false, updatable = false)
    private Long poLineId;

    @Column(name = "ordered", nullable = false, updatable = true)
    private Long ordered;

    @Column(name = "received", nullable = false, updatable = true)
    private Long received;

    @Column(name = "remaining_Order", nullable = false, updatable = true)
    private Long remainingOrder;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;

    @Column(name = "cost_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal costPrice = BigDecimal.ZERO;

    @Column(name = "line_total", nullable = false, precision = 19, scale = 2)
    private BigDecimal lineTotal = BigDecimal.ZERO;

    @UpdateTimestamp
    @Column(name="modifiedDate", nullable=false)
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "received_id", nullable = false)
    private PurchaseReceive purchaseReceive;

}
