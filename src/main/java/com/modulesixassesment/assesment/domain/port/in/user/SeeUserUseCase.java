package com.modulesixassesment.assesment.domain.port.in.user;

import com.modulesixassesment.assesment.domain.model.User;

import java.util.Optional;

public interface SeeUserUseCase {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
