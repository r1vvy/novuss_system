package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

public record Licence(
        UUID id,
        String licenceNumber,
        String licenceType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
