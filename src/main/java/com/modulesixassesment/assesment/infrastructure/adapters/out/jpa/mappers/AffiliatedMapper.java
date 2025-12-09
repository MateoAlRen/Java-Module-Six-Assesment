package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.mappers;

import com.modulesixassesment.assesment.domain.model.Affiliated;
import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.entity.AffiliatedEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AffiliatedMapper {
    Affiliated toDomain(AffiliatedEntity affiliated);
    AffiliatedEntity toEntity(Affiliated affiliated);
}
