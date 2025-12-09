package com.modulesixassesment.assesment.domain.port.in.request;

import com.modulesixassesment.assesment.domain.model.Request;

public interface CreateRequestUseCase {
    Request save(Request request);
}
