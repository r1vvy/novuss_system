package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Club(
        String id,
        String title,
        String image,
        Location location,
        Person contactPerson,
        Instant createdAt,
        Instant updatedAt
) {
}
