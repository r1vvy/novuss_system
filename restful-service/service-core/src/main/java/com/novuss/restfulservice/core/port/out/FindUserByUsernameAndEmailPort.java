package com.novuss.restfulservice.core.port.out;

import com.restfulservice.domain.User;

import java.util.Optional;

public interface FindUserByUsernameAndEmailPort {
    Optional<User> findUserByUsernameAndEmail(String username, String email);
}
