package com.modulesixassesment.assesment.infrastructure.adapters;

import com.modulesixassesment.assesment.domain.enums.RequestStatus;
import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestJpaRepository extends JpaRepository<RequestEntity, Long> {
    List<RequestEntity> findByDocumentAffiliated(String document);
    List<RequestEntity> findByStatus(RequestStatus status);
    Optional<RequestEntity> findById(Long id);
}
