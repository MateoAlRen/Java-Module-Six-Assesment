package com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.adapter;

import com.modulesixassesment.assesment.domain.model.User;
import com.modulesixassesment.assesment.domain.port.out.UserRepositoryPort;
import com.modulesixassesment.assesment.infrastructure.adapters.UserJpaRepository;
import com.modulesixassesment.assesment.infrastructure.adapters.out.jpa.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User u) {
        return userMapper.toDomain(userJpaRepository.save(userMapper.toEntity(u)));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(userMapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

}
