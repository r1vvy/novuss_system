package com.restfulservice.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public record Location(
        UUID id,
        ContactInfo contactInfo,
        String address,
        String city,
        BigDecimal latitude,
        BigDecimal longitude,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
