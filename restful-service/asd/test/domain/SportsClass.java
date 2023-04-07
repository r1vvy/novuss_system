package com.restfulservice.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public record SportsClass(
        UUID id,
        String title,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
