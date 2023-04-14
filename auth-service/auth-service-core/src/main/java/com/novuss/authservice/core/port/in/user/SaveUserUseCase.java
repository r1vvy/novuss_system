package com.novuss.authservice.core.port.in.user;

import com.novuss.authservice.domain.User;

public interface SaveUserUseCase {
    User save(User user);
}
