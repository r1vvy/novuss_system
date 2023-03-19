package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

public record Category (
        UUID id,
        String title,
        String color,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
