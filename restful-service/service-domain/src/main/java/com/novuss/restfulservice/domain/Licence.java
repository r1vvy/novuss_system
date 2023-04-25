package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.Date;

@Builder
public record Licence(
    String id,
    String title,
    Date issuedDate,
    Instant createdAt,
    Instant updatedAt
) {
}
