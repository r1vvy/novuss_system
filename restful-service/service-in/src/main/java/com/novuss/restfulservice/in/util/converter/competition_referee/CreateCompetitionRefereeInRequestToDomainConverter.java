package com.novuss.restfulservice.in.util.converter.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionRefereeInRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCompetitionRefereeInRequestToDomainConverter {
    public static CompetitionReferee convert(String competitionId, String refereeId, CreateCompetitionRefereeInRequest request) {
        return CompetitionReferee.builder()
                .competitionId(UUID.fromString(competitionId))
                .refereeId(UUID.fromString(refereeId))
                .role(request.role())
                .build();
    }
}
