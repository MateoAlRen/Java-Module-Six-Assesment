package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.adapter;

import com.modulesixassesment.assesment.domain.model.Affiliated;
import com.modulesixassesment.assesment.domain.port.out.AffiliatedRepositoryPort;
import com.modulesixassesment.assesment.infrastructure.adapters.AffiliatedJpaRepository;
import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.mappers.AffiliatedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AffiliatedJpaAdapter implements AffiliatedRepositoryPort {

    private final AffiliatedJpaRepository affiliatedJpaRepository;
    private final AffiliatedMapper affiliatedMapper;

    @Override
    public Affiliated save(Affiliated affiliated) {
        return affiliatedMapper.toDomain(affiliatedJpaRepository.save(affiliatedMapper.toEntity(affiliated)));
    }


    @Override
    public Optional<Affiliated> findByDocument(String document) {
        return affiliatedJpaRepository.findByDocument(document).map(affiliatedMapper::toDomain);
    }

    @Override
    public boolean existsByDocument(String document) {
        return affiliatedJpaRepository.existsByDocument(document);
    }
}
