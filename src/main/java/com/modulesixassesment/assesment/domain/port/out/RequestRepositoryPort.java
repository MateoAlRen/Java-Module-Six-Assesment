package com.modulesixassesment.assesment.domain.port.out;

import com.modulesixassesment.assesment.domain.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestRepositoryPort {
    Request save(Request request);
    Optional<Request> findById(Long id);
    List<Request> findByDocumentAffiliated(String document);
    List<Request> findByStatusPending();
    List<Request> findAll();
    void deleteById(Long id);
}
