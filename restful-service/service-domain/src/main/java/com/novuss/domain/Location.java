package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Location(
        UUID id,
        ContactInfo contactInfo,
        String address,
        String city,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
