package com.modulesixassesment.assesment.aplication.usecases.request;

import com.modulesixassesment.assesment.domain.enums.AffiliatedStatus;
import com.modulesixassesment.assesment.domain.enums.RequestStatus;
import com.modulesixassesment.assesment.domain.enums.RiskLevel;
import com.modulesixassesment.assesment.domain.model.Affiliated;
import com.modulesixassesment.assesment.domain.model.Request;
import com.modulesixassesment.assesment.domain.model.RiskEvaluation;
import com.modulesixassesment.assesment.domain.port.in.request.CreateRequestUseCase;
import com.modulesixassesment.assesment.domain.port.out.AffiliatedRepositoryPort;
import com.modulesixassesment.assesment.domain.port.out.RequestRepositoryPort;
import com.modulesixassesment.assesment.domain.port.out.RiskClientPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateRequestService implements CreateRequestUseCase {

    private final AffiliatedRepositoryPort affiliatedRepositoryPort;
    private final RequestRepositoryPort requestRepositoryPort;
    private final RiskClientPort riskClientPort;

    @Override
    public Request save(Request request) {
        Affiliated affiliated = affiliatedRepositoryPort.findByDocument(request.getDocumentAffiliated())
                .orElseThrow(() -> new RuntimeException("Affiliated not exists!"));
        if (affiliated.getStatus() != AffiliatedStatus.ACTIVE){
            throw new IllegalArgumentException("Member not active");
        }

        // old member check
        if (Period.between(affiliated.getDateAffiliated(), LocalDate.now()).toTotalMonths() < 6){
            throw new IllegalArgumentException("The member need almost six months to do a request");
        }

        // check amount and basic amount
        if (request.getAmount() <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (request.getMonths() <= 0) throw new IllegalArgumentException("Period must be > 0");

        request.setDateRequest(LocalDate.now());
        request.setStatus(RequestStatus.PENDING);

        // Save the request first
        Request saved = requestRepositoryPort.save(request);

        // Call the REST service
        RiskEvaluation riskEvaluation = riskClientPort.evaluateRisk(affiliated.getDocument(), request.getAmount(), request.getMonths());

        // Business rules
        // 1) month amount approx
        double monthAmount = (request.getAmount() / Math.max(request.getMonths(),1)) * (1 + request.getAmountPropose()/100.0);
        double proportion = monthAmount / affiliated.getSalary();

        String motive = "";
        boolean approved = true;

        // rule: amount/entry <= 0.6
        if (proportion > 0.6) {
            approved = false;
            motive = "Fee too high!";
        }

        // rule: max amount about salary
        if (request.getAmount() > affiliated.getSalary() * 20) {
            approved = false;
            motive = motive.isEmpty() ? "Amount is greater than the base salary" : motive + "; Amount is too high!";
        }

        // rule: check score
        if (riskEvaluation.getLevel() == RiskLevel.HIGH) {
            approved = false;
            motive = motive.isEmpty() ? "The risk is too high!" : motive + "; High risk level";
        }

        // If there's no motive, motive = approved/rejected default
        if (approved) {
            riskEvaluation.setMotive("Approved");
            saved.setStatus(RequestStatus.APPROVED);
        } else {
            riskEvaluation.setMotive(motive);
            saved.setStatus(RequestStatus.REJECTED);
        }

        saved.setEvaluation(riskEvaluation);

        // save
        return requestRepositoryPort.save(saved);
    }
}
