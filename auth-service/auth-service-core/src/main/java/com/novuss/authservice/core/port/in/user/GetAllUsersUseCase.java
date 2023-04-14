package com.novuss.authservice.core.port.in.user;

import com.novuss.authservice.domain.User;

import java.util.List;

public interface GetAllUsersUseCase {
    List<User> getAllUsers();
}
