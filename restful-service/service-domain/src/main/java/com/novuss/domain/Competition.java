package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

public record Competition(
        UUID id,
        String title,
        String description,
        Integer capacity,
        String imageLocation,
        LocalDate registrationOpenAt,
        LocalDate registrationCloseAt,
        LocalDate startsAt,
        LocalDate endsAt,
        LocalDate createdAt,
        LocalDate updatedAt,
        Category category,
        ContactInfo contactInfo
) {
}
