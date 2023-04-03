package com.novuss.restfulservice.core.port.in;

import com.restfulservice.domain.User;

public interface CreateUserUseCase {

    public User createUser(User user);
}
