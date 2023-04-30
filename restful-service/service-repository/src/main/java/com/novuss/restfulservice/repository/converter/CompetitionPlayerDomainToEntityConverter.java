package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
import com.novuss.restfulservice.repository.entity.CompetitionPlayerEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionPlayerDomainToEntityConverter {
    public static CompetitionPlayerEntity convert(CompetitionPlayer competitionPlayer) {
        var key = CompetitionPlayerCompositeKey.builder()
                .playerEntityId(competitionPlayer.playerId())
                .competitionEntityId(competitionPlayer.competitionId())
                .build();

        return CompetitionPlayerEntity.builder()
                .id(key)
                .placement(competitionPlayer.placement())
                .ratingChange(competitionPlayer.ratingChange())
                .ratingAfter(competitionPlayer.ratingAfter())
                .createdAt(competitionPlayer.createdAt())
                .updatedAt(competitionPlayer.updatedAt())
                .build();
    }
}
