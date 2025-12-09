package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.adapter;

import com.modulesixassesment.assesment.domain.enums.RiskLevel;
import com.modulesixassesment.assesment.domain.model.RiskEvaluation;
import com.modulesixassesment.assesment.domain.port.out.RiskClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RiskRestAdapter implements RiskClientPort {

    private final RestTemplate restTemplate;
    private final String riskUrl = "http://localhost:8081/risk-evaluation";

    @Override
    public RiskEvaluation evaluateRisk(String document, double amount, int months) {
        Map<String,Object> payload = Map.of(
                "document", document,
                "amount", amount,
                "months", months
        );
        ResponseEntity<Map> resp = restTemplate.postForEntity(riskUrl, payload, Map.class);
        Map body = resp.getBody();
        int score = (Integer) body.get("score");
        String level = (String) body.get("RiskLevel");
        String detail = (String) body.get("detail");
        RiskLevel levelEnum = RiskLevel.valueOf(level);
        RiskEvaluation eval = new RiskEvaluation(score, levelEnum, detail, null);
        return eval;
    }
}
