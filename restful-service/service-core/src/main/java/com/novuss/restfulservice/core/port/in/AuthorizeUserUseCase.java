package com.novuss.restfulservice.core.port.in;

import com.novuss.restfulservice.domain.UserRole;

import java.util.List;

public interface AuthorizeUserUseCase {
    String authorize(String token, List<UserRole> requiredAuthorities);
}
