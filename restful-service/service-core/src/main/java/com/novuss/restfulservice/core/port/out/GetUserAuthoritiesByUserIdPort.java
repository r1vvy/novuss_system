package com.novuss.restfulservice.core.port.out;

import com.novuss.restfulservice.domain.UserRole;

import java.util.List;

public interface GetUserAuthoritiesByUserIdPort {
    List<UserRole> getUserAuthoritiesByUserId(String id);
}
