package com.novuss.restfulservice.in.util.converter.sports_class;

import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.in.dto.request.UpdateSportsClassInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateSportsClassInRequestToDomainConverter {

    public static SportsClass convert(UpdateSportsClassInRequest request) {
        return SportsClass.builder()
                .title(request.title())
                .build();
    }
}
