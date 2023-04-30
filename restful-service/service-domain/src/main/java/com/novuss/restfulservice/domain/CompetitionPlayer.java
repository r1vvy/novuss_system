package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record CompetitionPlayer(
        UUID competitionId,
        UUID playerId,
        Integer placement,
        Integer ratingAfter,
        Integer ratingChange,
        Instant createdAt,
        Instant updatedAt
) {
}
