package com.novuss.restfulservice.core.port.out;

import com.restfulservice.domain.User;

public interface SaveUserPort {
    User saveUser(User user);
}
