package com.novuss.restfulservice.in.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.in.dto.RefereeCategoryDto;
import lombok.Builder;
import java.time.Instant;

@Builder
public record RefereeResponse(
        String id,
        String city,
        String commissionNumber,
        Instant createdAt,
        Instant updatedAt,
        RefereeCategoryDto category,
        Person person
) {

}
