package com.novuss.authservice.core.port.in.user;

import com.novuss.authservice.domain.User;

public interface FindUserByIdUseCase {
    User findUserById(String id);
}
