package com.novuss.authservice.in.dto.response;

import com.novuss.authservice.domain.UserRole;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
public record GetUserInResponse(
        UUID id,
        String username,
        String email,
        Set<UserRole> roles,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
