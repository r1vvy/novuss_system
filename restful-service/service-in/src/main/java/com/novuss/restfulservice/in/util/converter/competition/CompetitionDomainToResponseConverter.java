package com.novuss.restfulservice.in.util.converter.competition;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.in.dto.response.CompetitionResponse;
import org.springframework.stereotype.Component;

@Component
public class CompetitionDomainToResponseConverter {
    public static CompetitionResponse convert(Competition domain) {
        return CompetitionResponse.builder()
                .id(domain.id().toString())
                .title(domain.title())
                .image(domain.image())
                .registrationStart(domain.registrationStart())
                .registrationEnd(domain.registrationEnd())
                .competitionStart(domain.competitionStart())
                .competitionEnd(domain.competitionEnd())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .build();
    }
}
