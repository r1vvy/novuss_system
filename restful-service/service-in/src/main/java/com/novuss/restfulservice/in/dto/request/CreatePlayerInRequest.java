package com.novuss.restfulservice.in.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.novuss.restfulservice.domain.Gender;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.in.util.GenderDeserializer;
import lombok.Builder;

@Builder
public record CreatePlayerInRequest(
        String image,
        Integer rating,
        @JsonDeserialize(using = GenderDeserializer.class)
        Gender gender,
        Person person
) {

}
