package com.monocept.App.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "insurance_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String description;

    private Boolean active = true;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    
}