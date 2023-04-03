package com.novuss.restfulservice.in.dto;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record GetUserInResponse(
        UUID id,
        String username,
        String email,
        Enum<UserRole> role,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
