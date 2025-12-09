package com.modulesixassesment.assesment.infrastructure.adapters.in.web.mapper;

import com.modulesixassesment.assesment.domain.model.User;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    User toDomain(RegisterRequest r);
    RegisterRequest toRequest(User u);
}
