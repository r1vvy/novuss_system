package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record Club(
        UUID id,
        String title,
        String image,
        Location location,
        Person contactPerson,
        Instant createdAt,
        Instant updatedAt
) {
}
