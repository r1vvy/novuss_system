package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

public record Attachment(
        UUID id,
        String title,
        String fileLocation,
        LocalDate createdAt,
        LocalDate updatedAt,
        Competition competition
) {
}
