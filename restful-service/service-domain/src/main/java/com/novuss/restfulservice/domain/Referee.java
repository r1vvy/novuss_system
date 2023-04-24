package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder(toBuilder = true)
public record Referee(
    UUID id,
    String city,
    String commissionNumber,
    Instant createdAt,
    Instant updatedAt,
    RefereeCategory category,
    Person person
) {
}
