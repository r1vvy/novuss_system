package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record PlayerResponse(
        String id,
        PersonResponse person,
        ClubResponse club,
        LicenceResponse licence,
        SportsClassResponse sportsClass,
        Integer rating,
        String gender,
        Instant createdAt,
        Instant updatedAt
) {

}
