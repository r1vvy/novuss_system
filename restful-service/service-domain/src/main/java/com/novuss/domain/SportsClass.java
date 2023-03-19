package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

public record SportsClass(
        UUID id,
        String title,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
