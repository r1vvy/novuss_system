package com.novuss.restfulservice.in.dto.request;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.Builder;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Builder
public record UpdateRefereeInRequest(
        UUID id,
        String city,
        String commissionNumber,
        Date dateIssued,
        RefereeCategory refereeCategory,
        Person person
) {
}
