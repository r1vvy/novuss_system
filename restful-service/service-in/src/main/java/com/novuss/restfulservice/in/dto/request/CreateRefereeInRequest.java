package com.novuss.restfulservice.in.dto.request;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.RefereeCategory;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public record CreateRefereeInRequest(
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
