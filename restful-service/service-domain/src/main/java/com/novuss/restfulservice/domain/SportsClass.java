package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record SportsClass(
        UUID id,
        String title,
        Instant createdAt,
        Instant updatedAt
) {
}
