package com.novuss.restfulservice.in.util.converter.club;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.in.dto.request.UpdateClubInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateClubInRequestToDomainConverter {
    public static Club convert(UpdateClubInRequest updateClubInRequest) {
        return Club.builder()
            .title(updateClubInRequest.title())
            .location(updateClubInRequest.location())
            .build();
    }
}
