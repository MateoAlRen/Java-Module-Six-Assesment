package com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto;

public record RiskEvaluationDTO (
        int score,
        String level,
        String detail,
        String motive
){
}
