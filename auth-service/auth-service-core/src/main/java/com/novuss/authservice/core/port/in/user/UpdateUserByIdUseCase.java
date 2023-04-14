package com.novuss.authservice.core.port.in.user;

import com.novuss.authservice.domain.User;

public interface UpdateUserByIdUseCase {
    User updateUserById(String id, User user);
}
