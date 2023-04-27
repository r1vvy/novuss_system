package com.novuss.restfulservice.in.dto.request;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.SportsClass;
import lombok.Builder;


@Builder
public record UpdatePlayerInRequest(
        String image,
        Integer rating,
        String gender
) {
}
