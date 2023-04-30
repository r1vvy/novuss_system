package com.novuss.restfulservice.in.util.converter.sportsClass;

import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.in.dto.request.CreateSportsClassInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateSportsClassInRequestToDomainConverter {
    public static SportsClass convert(CreateSportsClassInRequest request) {
        return SportsClass.builder()
                .title(request.title())
                .build();
    }
}
