package com.novuss.restfulservice.in.util.converter.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionRefereeInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateCompetitionRefereeInRequestToDomainConverter {
    public static CompetitionReferee convert(CreateCompetitionRefereeInRequest request) {
        return CompetitionReferee.builder()
                .role(request.role())
                .build();
    }
}
