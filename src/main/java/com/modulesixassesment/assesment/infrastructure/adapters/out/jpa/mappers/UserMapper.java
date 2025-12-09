package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.mappers;

import com.modulesixassesment.assesment.domain.model.User;
import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
}
