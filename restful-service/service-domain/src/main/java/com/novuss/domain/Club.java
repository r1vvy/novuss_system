package com.novuss.domain;

import lombok.Builder;
import lombok.Value;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

public record Club(
        UUID id,
        String title,
        String image,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        ContactInfo contactInfo
) {
}
