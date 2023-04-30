package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Builder(toBuilder = true)
public record Competition(
        UUID id,
        CompetitionCategory category,
        Person contactPerson,
        Location location,
        String title,
        String image,
        Instant registrationStart,
        Instant registrationEnd,
        Instant competitionStart,
        Instant competitionEnd,
        Instant createdAt,
        Instant updatedAt
) {
}
