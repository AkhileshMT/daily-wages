package com.realworld.wages.entities;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@RequiredArgsConstructor
@Hidden
@Table(name = "purchase_receive")
public class PurchaseReceive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "received_id",nullable = false, updatable = false)
    private Long receivedId;

    @Column(name = "po_id", nullable = false)
    private Long poId;


    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;


    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;


    @Column(name = "total_quantity", nullable = false)
    private Long totalQuantity;


    @Column(name = "updated_total_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal updatedTotalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "purchaseReceive",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<PurchaseReceiveLineItem> lineItems = new ArrayList<>();


}
