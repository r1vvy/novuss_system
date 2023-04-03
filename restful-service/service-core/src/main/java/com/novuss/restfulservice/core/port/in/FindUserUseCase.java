package com.novuss.restfulservice.core.port.in;

import com.restfulservice.domain.User;

public interface FindUserUseCase {
    User findUser(String id);
}
