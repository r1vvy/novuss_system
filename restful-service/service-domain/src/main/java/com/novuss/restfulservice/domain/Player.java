package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record Player(
        UUID id,
        Integer rating,
        Gender gender,
        Person person,
        Club club,
        Licence licence,
        SportsClass sportsClass,
        Instant createdAt,
        Instant updatedAt
) {
}
