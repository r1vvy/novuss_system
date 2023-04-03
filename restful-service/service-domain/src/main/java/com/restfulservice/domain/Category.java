package com.restfulservice.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

public record Category (
        UUID id,
        String title,
        String color,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
