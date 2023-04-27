package com.novuss.restfulservice.in.converter.club;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.in.dto.request.CreateClubInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateClubInRequestToDomainConverter {
    public static Club convert(CreateClubInRequest request) {
        return Club.builder()
            .title(request.title())
            .image(request.image())
            .location(request.location())
            .build();
    }
}
