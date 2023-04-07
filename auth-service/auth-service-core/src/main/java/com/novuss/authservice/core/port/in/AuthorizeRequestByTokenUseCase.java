package com.novuss.authservice.core.port.in;

import com.novuss.authservice.domain.UserRole;

import java.util.List;

public interface AuthorizeRequestByTokenUseCase {

    boolean authorize(String token, List<UserRole> requiredAuthorities);
}
