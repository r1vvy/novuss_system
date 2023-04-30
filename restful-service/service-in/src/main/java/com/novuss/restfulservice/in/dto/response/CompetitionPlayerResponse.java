package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record CompetitionPlayerResponse(
        String competitionId,
        String playerId,
        Integer placement,
        Integer ratingAfter,
        Integer ratingChange,
        Instant createdAt,
        Instant updatedAt
) {
}
