package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record Location(
        UUID id,
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
