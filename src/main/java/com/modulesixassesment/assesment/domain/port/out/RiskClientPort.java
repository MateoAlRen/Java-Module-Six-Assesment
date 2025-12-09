package com.modulesixassesment.assesment.domain.port.out;

import com.modulesixassesment.assesment.domain.model.RiskEvaluation;

public interface RiskClientPort {
    RiskEvaluation evaluateRisk(String document, double amount, int months );
}
