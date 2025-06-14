package com.realworld.wages.entities;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Hidden
@Entity
@Table(name = "storeEarning")
public class storeEarning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeEarningId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "amountAdded", nullable = false)
    private Long amountAdded;

    @CreationTimestamp
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "modifiedDate", nullable = false)
    private Date modifiedDate;

    @Column(name="currentAmount", nullable = false)
    private Long currentAmount;

}
