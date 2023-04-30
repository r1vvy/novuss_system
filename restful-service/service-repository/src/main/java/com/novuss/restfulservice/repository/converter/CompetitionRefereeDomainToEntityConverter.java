package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.repository.CompetitionRefereeCompositeKey;
import com.novuss.restfulservice.repository.entity.CompetitionRefereeEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionRefereeDomainToEntityConverter {
    public static CompetitionRefereeEntity convert(CompetitionReferee domain) {
        var key = CompetitionRefereeCompositeKey.builder()
                .competitionEntityId(domain.competitionId())
                .refereeEntityId(domain.refereeId())
                .build();

        var builder = CompetitionRefereeEntity.builder()
                .id(key)
                .role(domain.role())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt());

        return builder.build();
    }
}
