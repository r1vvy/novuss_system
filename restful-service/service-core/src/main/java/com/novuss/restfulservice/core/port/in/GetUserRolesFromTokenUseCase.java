package com.novuss.restfulservice.core.port.in;

import com.restfulservice.domain.UserRole;

import java.util.List;

public interface GetUserRolesFromTokenUseCase {
    List<UserRole> getUserRolesFromToken(String token);
}
