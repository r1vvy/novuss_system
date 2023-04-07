package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Builder
public record Referee(
    UUID id,
    String city,
    String commissionNumber,
    Date dateIssued,
    Instant createdAt,
    Instant updatedAt,
    RefereeCategory refereeCategory,
    Person person
) {
}
