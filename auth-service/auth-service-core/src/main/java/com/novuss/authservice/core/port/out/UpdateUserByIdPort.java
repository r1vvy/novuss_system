package com.novuss.authservice.core.port.out;

import com.novuss.authservice.domain.User;

public interface UpdateUserByIdPort {
    User updateById(String id, User user);
}
