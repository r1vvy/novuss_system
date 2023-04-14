package com.novuss.authservice.core.port.in.token;

import com.novuss.authservice.domain.UserRole;

import java.util.List;

public interface AuthorizeRequestByTokenUseCase {

    boolean authorizeByRequiredAuthorities(String token, List<UserRole> requiredAuthorities);
    boolean authorizeByUserId(String token, String userId);
}
