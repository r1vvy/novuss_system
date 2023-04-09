package com.novuss.restfulservice.in.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.Builder;
import java.time.Instant;
import java.util.UUID;

@Builder
public record RefereeInResponse(
        String id,
        String city,
        String commissionNumber,
        Instant createdAt,
        Instant updatedAt,
        RefereeCategory refereeCategory,
        Person person
) {

}
