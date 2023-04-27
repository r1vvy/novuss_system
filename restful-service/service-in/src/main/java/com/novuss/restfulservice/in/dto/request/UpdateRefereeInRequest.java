package com.novuss.restfulservice.in.dto.request;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record UpdateRefereeInRequest(
        String city,
        String commissionNumber
) {
}
