package com.modulesixassesment.assesment.aplication.usecases.user;

import com.modulesixassesment.assesment.domain.model.User;
import com.modulesixassesment.assesment.domain.port.in.user.SeeUserUseCase;
import com.modulesixassesment.assesment.domain.port.out.UserRepositoryPort;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.exceptions.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SeeUserService implements SeeUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> found = userRepositoryPort.findByUsername(username);

        if (found.isEmpty()){
            return null;
        }

        return found;
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean found = userRepositoryPort.existsByUsername(username);

        if (!found){
            throw new NotFoundException("Theres no user with the name: " + username);
        }

        return found;
    }
}
