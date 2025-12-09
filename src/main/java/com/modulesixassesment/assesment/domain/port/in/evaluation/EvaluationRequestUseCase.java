package com.modulesixassesment.assesment.domain.port.in.evaluation;

import com.modulesixassesment.assesment.domain.model.Request;

public interface EvaluationRequestUseCase {
    Request evaluate(Long requestId);
}
