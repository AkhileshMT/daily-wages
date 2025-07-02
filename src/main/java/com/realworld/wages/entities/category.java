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
@Entity
@RequiredArgsConstructor
@Hidden
@Table(name = "category")
public class category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category_name", unique = true, nullable = false, length = 50)
    private String categoryName;

    @CreationTimestamp
    @Column(name="createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name="modifiedDate", nullable=false)
    private Date modifiedDate;

    @Column(name="isActive", nullable=false, insertable=false, columnDefinition = "boolean default true")
    private boolean active;

}
