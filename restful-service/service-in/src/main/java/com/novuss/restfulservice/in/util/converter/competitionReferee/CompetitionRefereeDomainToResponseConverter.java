package com.novuss.restfulservice.in.util.converter.competitionReferee;

import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.in.dto.response.CompetitionRefereeResponse;
import org.springframework.stereotype.Component;

@Component
public class CompetitionRefereeDomainToResponseConverter {
    public static CompetitionRefereeResponse convert(CompetitionReferee domain) {
        return CompetitionRefereeResponse.builder()
                        .refereeId(domain.refereeId().toString())
                        .competitionId(domain.competitionId().toString())
                        .role(domain.role())
                        .createdAt(domain.createdAt())
                        .updatedAt(domain.updatedAt())
                        .build();
    }
}
