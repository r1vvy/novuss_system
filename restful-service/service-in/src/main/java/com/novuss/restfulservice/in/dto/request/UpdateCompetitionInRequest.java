package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record UpdateCompetitionInRequest(
        String title,
        String image,
        Instant registrationStart,
        Instant registrationEnd,
        Instant competitionStart,
        Instant competitionEnd
) {
}
