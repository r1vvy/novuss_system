package com.novuss.authservice.in.dto.request;

import com.novuss.authservice.domain.UserRole;
import lombok.Builder;

import java.util.List;

public record AuthorizationRequest(
        List<UserRole> requiredAuthorities
) {
}
