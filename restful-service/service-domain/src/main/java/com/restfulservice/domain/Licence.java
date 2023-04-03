package com.restfulservice.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public record Licence(
        UUID id,
        String licenceNumber,
        String licenceType,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
