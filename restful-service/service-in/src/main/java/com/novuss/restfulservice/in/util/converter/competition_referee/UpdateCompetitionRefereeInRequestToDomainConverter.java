package com.novuss.restfulservice.in.util.converter.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionRefereeInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompetitionRefereeInRequestToDomainConverter {
    public static CompetitionReferee convert(UpdateCompetitionRefereeInRequest request) {
        return CompetitionReferee.builder()
                .role(request.role())
                .build();
    }
}
