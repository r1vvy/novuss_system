package com.novuss.restfulservice.in.util.converter.competition;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateCompetitionInRequestToDomainConverter {
    public static Competition convert(CreateCompetitionInRequest createCompetitionInRequest) {
        return Competition.builder()
                .title(createCompetitionInRequest.title())
                .competitionStart(createCompetitionInRequest.competitionStart())
                .competitionEnd(createCompetitionInRequest.competitionEnd())
                .registrationStart(createCompetitionInRequest.registrationStart())
                .registrationEnd(createCompetitionInRequest.registrationEnd())
                .build();
    }
}
