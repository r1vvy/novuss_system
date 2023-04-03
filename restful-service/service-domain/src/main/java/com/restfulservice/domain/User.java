package com.restfulservice.domain;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
public record User(
        UUID id,
        String username,
        String email,
        String password,
        Enum role,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
