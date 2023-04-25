package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ClubResponse(
        String id,
        String title,
        String image,
        LocationResponse location,
        PersonResponse contactPerson,
        Instant createdAt,
        Instant updatedAt
) {
}
