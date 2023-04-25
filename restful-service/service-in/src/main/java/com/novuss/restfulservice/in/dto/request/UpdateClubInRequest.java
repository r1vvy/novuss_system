package com.novuss.restfulservice.in.dto.request;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.domain.Person;
import lombok.Builder;

@Builder
public record UpdateClubInRequest(
        String title,
        String image,
        Location location,
        Person contactPerson
) {
}
