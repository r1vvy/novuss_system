package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record Player(
        UUID id,
        String image,
        Integer rating,
        Gender gender,
        Instant createdAt,
        Instant updatedAt,
        Person person
) {
}
