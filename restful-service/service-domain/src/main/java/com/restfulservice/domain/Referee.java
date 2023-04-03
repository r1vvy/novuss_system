package com.restfulservice.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public record Referee(
        UUID id,
        Person person,
        String city,
        String category,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
