package com.novuss.restfulservice.in.dto.response;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record RefereeInResponse(
        String id,
        String city,
        String commissionNumber,
        LocalDate dateIssued,
        Instant createdAt,
        Instant updatedAt,
        RefereeCategory refereeCategory,
        Person person
) {

}
