package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder(toBuilder = true)
public record LocationResponse(
        String id,
        String title,
        String city,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        PersonResponse contactPerson,
        Instant createdAt,
        Instant updatedAt
) {
}
