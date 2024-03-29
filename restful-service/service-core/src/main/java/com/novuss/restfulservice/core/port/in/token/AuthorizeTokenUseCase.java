package com.novuss.restfulservice.core.port.in.token;

import com.novuss.restfulservice.domain.UserRole;

import java.util.List;

public interface AuthorizeTokenUseCase {
    void authorize(String token, List<UserRole> requiredAuthorities);
}
