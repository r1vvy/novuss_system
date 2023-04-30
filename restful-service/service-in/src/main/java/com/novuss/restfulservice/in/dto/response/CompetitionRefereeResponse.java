package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record CompetitionRefereeResponse(
        String refereeId,
        String competitionId,
        String role,
        Instant createdAt,
        Instant updatedAt
) {
}
