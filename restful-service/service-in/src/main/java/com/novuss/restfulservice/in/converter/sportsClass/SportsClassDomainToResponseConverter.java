package com.novuss.restfulservice.in.converter.sportsClass;

import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.in.dto.response.SportsClassResponse;
import org.springframework.stereotype.Component;

@Component
public class SportsClassDomainToResponseConverter {
    public static SportsClassResponse convert(SportsClass domain) {
        return SportsClassResponse.builder()
                .id(domain.id())
                .title(domain.title())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .build();
    }
}
