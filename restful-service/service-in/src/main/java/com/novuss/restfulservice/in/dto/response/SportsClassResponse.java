package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record SportsClassResponse(
        String id,
        String title,
        Instant createdAt,
        Instant updatedAt
) {
}
