package com.novuss.restfulservice.in.util.converter.competition;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompetitionInRequestToDomainConverter {
    public static Competition convert(UpdateCompetitionInRequest request) {
        return Competition.builder()
                .title(request.title())
                .registrationStart(request.registrationStart())
                .registrationEnd(request.registrationEnd())
                .competitionStart(request.competitionStart())
                .competitionEnd(request.competitionEnd())
                .build();
    }
}
