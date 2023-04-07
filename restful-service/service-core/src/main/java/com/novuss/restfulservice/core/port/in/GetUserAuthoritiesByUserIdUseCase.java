package com.novuss.restfulservice.core.port.in;

import com.restfulservice.domain.UserRole;

import java.util.List;

public interface GetUserAuthoritiesByUserIdUseCase {
    List<UserRole> getUserAuthorityByUserId(String id);
}
