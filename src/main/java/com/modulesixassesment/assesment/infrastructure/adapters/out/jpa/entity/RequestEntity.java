package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.entity;

import com.modulesixassesment.assesment.domain.enums.RequestStatus;
import com.modulesixassesment.assesment.domain.enums.RiskLevel;
import com.modulesixassesment.assesment.domain.model.RiskEvaluation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private String documentAffiliated;
    private double amount;
    private int months;
    private double amountPropose;
    private LocalDate dateRequest;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    // Evaluation embebed
    private int score;
    private RiskLevel level;
    private String detail;
    private String motive;
}
