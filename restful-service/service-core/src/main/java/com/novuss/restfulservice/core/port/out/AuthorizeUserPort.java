package com.novuss.restfulservice.core.port.out;

import com.novuss.restfulservice.domain.UserRole;

import java.util.List;

public interface AuthorizeUserPort {

    void authorize(String token, List<UserRole> requiredAuthorities);
}
