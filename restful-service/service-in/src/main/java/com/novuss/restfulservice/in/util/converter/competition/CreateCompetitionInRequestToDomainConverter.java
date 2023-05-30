package com.novuss.restfulservice.in.util.converter.competition;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionInRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCompetitionInRequestToDomainConverter {
    public static Competition convert(CreateCompetitionInRequest createCompetitionInRequest) {
        return Competition.builder()
                .title(createCompetitionInRequest.title())
                .competitionStart(createCompetitionInRequest.competitionStart().toInstant())
                .competitionEnd(createCompetitionInRequest.competitionEnd().toInstant())
                .registrationStart(createCompetitionInRequest.registrationStart().toInstant())
                .registrationEnd(createCompetitionInRequest.registrationEnd().toInstant())
                .location(
                        Location.builder()
                                .id(UUID.fromString(createCompetitionInRequest.locationId()))
                                .build()
                )
                .category(
                        CompetitionCategory.builder()
                                .id(UUID.fromString(createCompetitionInRequest.categoryId()))
                                .build()
                )
                .build();
    }
}
