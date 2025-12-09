package com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto;

import com.modulesixassesment.assesment.domain.enums.AffiliatedStatus;

import java.time.LocalDate;

public record AffiliatedResponseDTO (
        String document,
        String name,
        Double salary,
        LocalDate affiliatedDate,
        AffiliatedStatus status
) {
}
