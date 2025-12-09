package com.modulesixassesment.assesment.aplication.usecases.user;

import com.modulesixassesment.assesment.domain.model.User;
import com.modulesixassesment.assesment.domain.port.in.user.LoginUserUseCase;
import com.modulesixassesment.assesment.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService implements LoginUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final UserRepositoryPort repositoryPort;

    @Override
    public User login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return repositoryPort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
    }
}
