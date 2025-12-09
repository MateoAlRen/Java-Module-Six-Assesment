package com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto;

import com.modulesixassesment.assesment.domain.enums.RequestStatus;
import com.modulesixassesment.assesment.domain.model.RiskEvaluation;

import java.time.LocalDate;

public record RequestResponseDTO(
        Long requestId,
        String documentAffiliated,
        double amount,
        int months,
        double amountPropose,
        LocalDate dateRequest,
        RequestStatus status,
        RiskEvaluation evaluation
) {
}
