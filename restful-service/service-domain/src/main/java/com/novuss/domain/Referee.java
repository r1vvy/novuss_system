package com.novuss.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public record Referee(
        UUID id,
        Person person,
        String city,
        Enum<RefereeCategory> category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
