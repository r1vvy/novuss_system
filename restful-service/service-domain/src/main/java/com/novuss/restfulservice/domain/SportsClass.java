package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;

@Builder
public record SportsClass(
        String id,
        String title,
        Instant createdAt,
        Instant updatedAt
) {
}
