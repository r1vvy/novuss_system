package com.novuss.authservice.core.port.in.user;

import com.novuss.authservice.domain.User;

public interface FindUserByUsernameOrEmailUseCase {
    User findUserByUsernameOrEmail(String username, String email);
}
