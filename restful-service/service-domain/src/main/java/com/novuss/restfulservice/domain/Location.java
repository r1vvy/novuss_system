package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record Location(
        String id,
        String title,
        String city,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        Person contactPerson,
        Instant createdAt,
        Instant updatedAt
) {
}
