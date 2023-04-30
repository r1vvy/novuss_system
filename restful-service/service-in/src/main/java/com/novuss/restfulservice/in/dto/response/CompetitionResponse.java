package com.novuss.restfulservice.in.dto.response;

import com.novuss.restfulservice.domain.Location;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;

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
        Location location,
        PersonResponse contactPerson
) {
}
