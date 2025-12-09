package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.mappers;

import com.modulesixassesment.assesment.domain.model.Request;
import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.entity.RequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    Request toModel(RequestEntity request);
    RequestEntity toEntity(Request request);
}
