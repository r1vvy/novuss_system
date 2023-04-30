package com.novuss.restfulservice.in.util.converter.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.in.dto.response.CompetitionPlayerResponse;
import org.springframework.stereotype.Component;

@Component
public class CompetitionPlayerDomainToResponseConverter {
    public static CompetitionPlayerResponse convert(CompetitionPlayer competitionPlayer) {
        return CompetitionPlayerResponse.builder()
                .competitionId(competitionPlayer.competitionId().toString())
                .playerId(competitionPlayer.playerId().toString())
                .placement(competitionPlayer.placement())
                .ratingAfter(competitionPlayer.ratingAfter())
                .ratingChange(competitionPlayer.ratingChange())
                .createdAt(competitionPlayer.createdAt())
                .updatedAt(competitionPlayer.updatedAt())
                .build();
    }
}
