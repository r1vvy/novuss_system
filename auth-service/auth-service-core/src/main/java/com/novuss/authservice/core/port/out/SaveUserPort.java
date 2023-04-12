package com.novuss.authservice.core.port.out;

import com.novuss.authservice.domain.User;

public interface SaveUserPort {
    User saveUser(User user);
}
