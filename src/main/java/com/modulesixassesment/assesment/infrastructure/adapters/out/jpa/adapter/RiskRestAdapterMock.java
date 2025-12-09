package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.adapter;

import com.modulesixassesment.assesment.domain.enums.RiskLevel;
import com.modulesixassesment.assesment.domain.model.RiskEvaluation;
import com.modulesixassesment.assesment.domain.port.out.RiskClientPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("mock-risk")
public class RiskRestAdapterMock implements RiskClientPort {

    @Override
    public RiskEvaluation evaluateRisk(String document, double amount, int months) {
        int score = (int) (Math.random() * 100);

        RiskLevel level = RiskLevel.LOW;
        if (amount > 20000) level = RiskLevel.HIGH;
        else if (amount > 10000) level = RiskLevel.MEDIUM;

        String detail = "Mock evaluation based on amount and months";
        return new RiskEvaluation(score, level, detail, null);
    }
}
