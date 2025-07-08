package com.realworld.wages.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "expenses")
public class dailyExpensive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expensesId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "description",nullable = false, length = 50)
    private String description;

    @Column(name = "ImageName", length = 100)
    private String name;

    @Column(name = "filePath", length = 100)
    private String filePath;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "category_name", length = 100)
    private String categoryName;

    @CreationTimestamp
    @Column(name="createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name="modifiedDate", nullable=false)
    private Date modifiedDate;

    @Column(name="categoryId", nullable = true, columnDefinition = "bigint(20) default 1")
    private Long categoryId=(long) 1;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="categoryId", referencedColumnName="categoryId",insertable=false, updatable=false)
    private category category;

}
