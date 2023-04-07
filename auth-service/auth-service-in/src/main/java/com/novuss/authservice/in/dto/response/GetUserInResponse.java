package com.novuss.authservice.in.dto.response;

import ch.qos.logback.core.status.InfoStatus;
import com.novuss.authservice.domain.UserRole;
import lombok.Builder;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
public record GetUserInResponse(
        UUID id,
        String username,
        String email,
        Set<UserRole> roles,
        Instant createdAt,
        Instant updatedAt
) {
}
