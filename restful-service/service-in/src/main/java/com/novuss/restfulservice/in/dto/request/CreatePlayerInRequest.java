package com.novuss.restfulservice.in.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.novuss.restfulservice.domain.*;
import com.novuss.restfulservice.in.util.GenderDeserializer;
import lombok.Builder;

@Builder
public record CreatePlayerInRequest(
        Integer rating,
        @JsonDeserialize(using = GenderDeserializer.class)
        Gender gender,
        Person person
) {

}
