package com.modulesixassesment.assesment.aplication.usecases.user;

import com.modulesixassesment.assesment.domain.model.User;
import com.modulesixassesment.assesment.domain.port.in.user.SaveUserUseCase;
import com.modulesixassesment.assesment.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements SaveUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder encoder;


    @Override
    public User save(User u) {
        if (userRepositoryPort.existsByUsername(u.getUsername())){
            throw new RuntimeException();
        }

        u.setPassword(encoder.encode(u.getPassword()));

        return userRepositoryPort.save(u);
    }
}
