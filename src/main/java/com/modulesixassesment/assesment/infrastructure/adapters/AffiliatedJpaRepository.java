package com.modulesixassesment.assesment.infrastructure.adapters;

import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.entity.AffiliatedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AffiliatedJpaRepository extends JpaRepository<AffiliatedEntity, Long> {
    Optional<AffiliatedEntity> findByDocument(String document);
    boolean existsByDocument(String document);
}
