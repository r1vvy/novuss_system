package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;

@Builder
public record RefereeCategory(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
