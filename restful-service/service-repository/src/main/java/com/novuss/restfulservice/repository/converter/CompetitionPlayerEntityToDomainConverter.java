package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.repository.entity.CompetitionPlayerEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionPlayerEntityToDomainConverter {
    public static CompetitionPlayer convert(CompetitionPlayerEntity entity) {
        return CompetitionPlayer.builder()
                .playerId(entity.getId().getPlayerEntityId())
                .competitionId(entity.getId().getCompetitionEntityId())
                .placement(entity.getPlacement())
                .ratingChange(entity.getRatingChange())
                .build();
    }
}
