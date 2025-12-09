package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.adapter;

import com.modulesixassesment.assesment.domain.enums.RequestStatus;
import com.modulesixassesment.assesment.domain.model.Request;
import com.modulesixassesment.assesment.domain.port.out.RequestRepositoryPort;
import com.modulesixassesment.assesment.infrastructure.adapters.RequestJpaRepository;
import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.mappers.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestJpaAdapter implements RequestRepositoryPort {

    private final RequestJpaRepository requestJpaRepository;
    private final RequestMapper requestMapper;

    @Override
    public Request save(Request request) {
        return requestMapper.toModel(requestJpaRepository.save(requestMapper.toEntity(request)));
    }

    @Override
    public Optional<Request> findById(Long id) {
        return requestJpaRepository.findById(id).map(requestMapper::toModel);
    }

    @Override
    public List<Request> findByDocumentAffiliated(String document) {
        return requestJpaRepository.findByDocumentAffiliated(document).stream().map(requestMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Request> findByStatusPending() {
        return requestJpaRepository.findByStatus(RequestStatus.PENDING)
                .stream().map(requestMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Request> findAll() {
        return requestJpaRepository.findAll().stream().map(requestMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        requestJpaRepository.deleteById(id);
    }
}
