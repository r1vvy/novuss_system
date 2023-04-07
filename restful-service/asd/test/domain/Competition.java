package com.restfulservice.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public record Competition(
        UUID id,
        String title,
        String description,
        Integer capacity,
        String imageLocation,
        ZonedDateTime registrationOpenAt,
        ZonedDateTime registrationCloseAt,
        ZonedDateTime startsAt,
        ZonedDateTime endsAt,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Category category,
        ContactInfo contactInfo
) {
}
