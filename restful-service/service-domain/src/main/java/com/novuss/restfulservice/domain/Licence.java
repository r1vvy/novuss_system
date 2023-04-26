package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Builder
public record Licence(
    UUID id,
    String title,
    Date issuedDate,
    Instant createdAt,
    Instant updatedAt
) {
}
