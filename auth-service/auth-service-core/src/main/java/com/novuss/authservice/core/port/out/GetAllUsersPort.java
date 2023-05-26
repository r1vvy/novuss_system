package com.novuss.authservice.core.port.out;

import com.novuss.authservice.domain.User;

import java.util.List;
import java.util.Optional;

public interface GetAllUsersPort {
    Optional<List<User>> getAllUsers();
}
