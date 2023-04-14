package com.novuss.authservice.in.dto.response;

import com.novuss.authservice.domain.UserRole;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record GetUserInResponse(
        String id,
        String username,
        String email,
        Set<UserRole> roles,
        Instant createdAt,
        Instant updatedAt
) {
}
