package com.modulesixassesment.assesment.infrastructure.adapters.in.web.controllers;

import com.modulesixassesment.assesment.aplication.usecases.user.CreateUserService;
import com.modulesixassesment.assesment.aplication.usecases.user.LoginUserService;
import com.modulesixassesment.assesment.domain.port.in.user.SaveUserUseCase;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user.AuthResponse;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user.LoginRequest;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user.RegisterRequest;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.mapper.UserWebMapper;
import com.modulesixassesment.assesment.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SaveUserUseCase registerService;
    private final LoginUserService loginService;
    private final JwtService jwtService;
    private final UserWebMapper userWebMapper;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        registerService.save(userWebMapper.toDomain(request));
        return "User Created Successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        var user = loginService.login(request.username(), request.password());
        var token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().name().replace("ROLE_", ""))
                        .build()
        );
        return new AuthResponse(token);
    }
}
