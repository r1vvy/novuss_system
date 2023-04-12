package com.novuss.authservice.core.port.out;

import com.novuss.authservice.domain.User;

import java.util.Optional;

public interface FindUserByIdPort {
    Optional<User> findUserById(String id);
}
