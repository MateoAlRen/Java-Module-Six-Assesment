package com.modulesixassesment.assesment.aplication.usecases.evaluation;

import com.modulesixassesment.assesment.domain.enums.RequestStatus;
import com.modulesixassesment.assesment.domain.model.Request;
import com.modulesixassesment.assesment.domain.port.in.evaluation.EvaluationRequestUseCase;
import com.modulesixassesment.assesment.domain.port.out.RequestRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluateRequestService implements EvaluationRequestUseCase {

    private final RequestRepositoryPort requestRepositoryPort;

    @Override
    public Request evaluate(Long requestId) {
        Request request = requestRepositoryPort.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not founded"));

        // If already its evaluated, just return
        if (request.getStatus() != null && request.getStatus() != RequestStatus.PENDING){
            return request;
        }

        throw new UnsupportedOperationException("Use CreateRequestService for the complete flow.");
    }
}
