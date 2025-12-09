package com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.user;

import com.modulesixassesment.assesment.domain.enums.Role;

public record RegisterRequest(String username, String password, Role role) {}

