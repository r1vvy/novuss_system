package com.novuss.restfulservice.in.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.novuss.restfulservice.in.dto.PersonDto;
import com.novuss.restfulservice.in.util.GenderDeserializer;
import com.restfulservice.domain.*;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record CreatePlayerInRequest(
        String image,
        Integer rating,
        @JsonDeserialize(using = GenderDeserializer.class)
        Gender gender,
        PersonDto person
) {

}
