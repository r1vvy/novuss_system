package com.novuss.restfulservice.in.dto.response;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.Builder;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Builder
public record RefereeInResponse(
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
