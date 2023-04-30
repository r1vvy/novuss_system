package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record CompetitionReferee(
        UUID refereeId,
        UUID competitionId,
        String role,
        Instant createdAt,
        Instant updatedAt
) {
}
