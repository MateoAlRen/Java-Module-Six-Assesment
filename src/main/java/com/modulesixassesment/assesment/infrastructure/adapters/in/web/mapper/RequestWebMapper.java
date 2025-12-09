package com.modulesixassesment.assesment.infrastructure.adapters.in.web.mapper;

import com.modulesixassesment.assesment.domain.model.Request;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.RequestRequestDTO;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.RequestResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestWebMapper {
    Request toModel(RequestRequestDTO requestDTO);
    RequestRequestDTO toDto(Request request);
    RequestResponseDTO toResponse(Request response);
}
