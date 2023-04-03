package com.restfulservice.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public record Club(
        UUID id,
        String title,
        String image,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        ContactInfo contactInfo
) {
}
