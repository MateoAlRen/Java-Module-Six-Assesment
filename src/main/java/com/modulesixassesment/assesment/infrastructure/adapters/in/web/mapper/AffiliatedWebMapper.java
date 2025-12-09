package com.modulesixassesment.assesment.infrastructure.adapters.in.web.mapper;

import com.modulesixassesment.assesment.domain.model.Affiliated;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.AffiliatedRequestDTO;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.AffiliatedResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AffiliatedWebMapper {
    Affiliated toModel(AffiliatedRequestDTO requestDTO);
    AffiliatedResponseDTO toDto(Affiliated affiliated);

}
