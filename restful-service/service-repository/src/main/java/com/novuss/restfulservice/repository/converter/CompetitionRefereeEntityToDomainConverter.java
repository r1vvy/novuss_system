package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.repository.entity.CompetitionRefereeEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionRefereeEntityToDomainConverter {
    public static CompetitionReferee convert(CompetitionRefereeEntity entity) {
        return CompetitionReferee.builder()
                .competitionId(entity.getId().getCompetitionEntityId())
                .refereeId(entity.getId().getRefereeEntityId())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
