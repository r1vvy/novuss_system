package com.novuss.restfulservice.in.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.novuss.restfulservice.domain.Gender;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.in.util.GenderDeserializer;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record CreatePlayerResponse(
        UUID id,
        String image,
        Integer rating,
        @JsonDeserialize(using = GenderDeserializer.class)
        Enum<Gender> gender,
        @JsonProperty("createdAt")
        Instant createdAt,
        @JsonProperty("updatedAt")
        Instant updatedAt,
        @JsonProperty("person")
        Person person
) {
}
