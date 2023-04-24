package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder(toBuilder = true)
public record RefereeCategory(
        UUID id,
        String title,
        Instant createdAt,
        Instant updatedAt,
        Set<Referee> referees
) {
}
