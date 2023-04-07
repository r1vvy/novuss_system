package com.restfulservice.domain;

import java.time.ZonedDateTime;

public record Attachment(
        Long id,
        String title,
        String fileLocation,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Competition competition
) {
}
