package com.novuss.restfulservice.in.util.converter.club;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.in.dto.request.CreateClubInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateClubInRequestToDomainConverter {
    public static Club convert(CreateClubInRequest request) {
        return Club.builder()
            .title(request.title())
            .location(request.location())
            .build();
    }
}
