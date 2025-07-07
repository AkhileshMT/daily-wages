package com.realworld.wages.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FileData")
@Builder
public class fileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "type", length = 100)
    private String type;

    @Column(name = "filePath", length = 100)
    private String filePath;

    @Transient
    private byte[] imageData;

    @Column(name="expenseId", nullable = true, columnDefinition = "bigint(20) default 1")
    private Long expenseId=(long) 1;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name="expenseId", referencedColumnName="expenseId",insertable=false, updatable=false)
//    private dailyExpensive expense;
}
