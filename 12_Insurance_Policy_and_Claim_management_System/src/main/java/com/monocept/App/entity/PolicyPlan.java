package com.monocept.App.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "policy_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;

    private Double coverageAmount;

    private Double premiumAmount;

    @Enumerated(EnumType.STRING)
    private PremiumType premiumType;

    private Integer duration;

    private String termsAndConditions;

    private Boolean active = true;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

   
}
