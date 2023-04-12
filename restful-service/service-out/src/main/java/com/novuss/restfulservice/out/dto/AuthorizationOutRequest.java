package com.novuss.restfulservice.out.dto;

import com.novuss.restfulservice.domain.UserRole;
import lombok.Builder;

import java.util.List;

@Builder
public record AuthorizationOutRequest(
        String token,
        List<UserRole> requiredAuthorities) {
}
