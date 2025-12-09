package com.modulesixassesment.assesment.domain.port.in.user;

import com.modulesixassesment.assesment.domain.model.User;

public interface LoginUserUseCase {
    User login(String username, String password);
}
