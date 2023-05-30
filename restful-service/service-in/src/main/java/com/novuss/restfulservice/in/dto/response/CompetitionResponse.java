package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record CompetitionResponse(
        String id,
        String title,
        String image,
        Instant registrationStart,
        Instant registrationEnd,
        Instant competitionStart,
        Instant competitionEnd,
        Instant createdAt,
        Instant updatedAt,
        CompetitionCategoryResponse category,
        LocationResponse location,
        PersonResponse contactPerson
) {
}
