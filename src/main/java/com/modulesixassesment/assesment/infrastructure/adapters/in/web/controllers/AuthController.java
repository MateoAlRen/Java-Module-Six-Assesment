package com.modulesixassesment.assesment.infrastructure.adapters.in.web.controllers;

import com.modulesixassesment.assesment.aplication.usecases.user.LoginUserService;
import com.modulesixassesment.assesment.domain.port.in.user.SaveUserUseCase;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user.AuthResponse;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user.LoginRequest;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user.RegisterRequest;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.mapper.UserWebMapper;
import com.modulesixassesment.assesment.infrastructure.security.JwtService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
public class AuthController {

    private final SaveUserUseCase registerService;
    private final LoginUserService loginService;
    private final JwtService jwtService;
    private final UserWebMapper userWebMapper;

    @Operation(
            summary = "Register a new user",
            description = "Allows a new user to register in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"message\":\"User Created Successfully\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content
                    )
            }
    )
    @Timed(value = "example.endpoint.time", description = "Time response /register")
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        registerService.save(userWebMapper.toDomain(request));
        return "User Created Successfully";
    }

    @Operation(
            summary = "Login a user",
            description = "Authenticates a user and returns a JWT token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentication successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = @ExampleObject(value = "{\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid credentials",
                            content = @Content
                    )
            }
    )
    @Timed(value = "example.endpoint.time", description = "Time response /login")
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

