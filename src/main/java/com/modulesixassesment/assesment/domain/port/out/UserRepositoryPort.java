package com.modulesixassesment.assesment.domain.port.out;

import com.modulesixassesment.assesment.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User u);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
