package com.novuss.restfulservice.in.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record CreateCompetitionInRequest(
        String title,
        String image,
        Instant registrationStart,
        Instant registrationEnd,
        Instant competitionStart,
        Instant competitionEnd
) {
}
