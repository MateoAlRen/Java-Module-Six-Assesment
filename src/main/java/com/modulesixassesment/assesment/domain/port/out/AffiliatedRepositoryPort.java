package com.modulesixassesment.assesment.domain.port.out;

import com.modulesixassesment.assesment.domain.model.Affiliated;

import java.util.Optional;

public interface AffiliatedRepositoryPort {
    Affiliated save(Affiliated affiliated);
    Optional<Affiliated> findByDocument(String document);
    boolean existsByDocument(String document);
}
