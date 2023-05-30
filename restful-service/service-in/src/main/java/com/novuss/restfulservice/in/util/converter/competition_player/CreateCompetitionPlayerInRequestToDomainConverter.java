package com.novuss.restfulservice.in.util.converter.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionPlayerInRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCompetitionPlayerInRequestToDomainConverter {
    public static CompetitionPlayer convert(String playerId, CreateCompetitionPlayerInRequest request) {
        return CompetitionPlayer.builder()
                .playerId(UUID.fromString(playerId))
                .placement(request.placement())
                .ratingChange(request.ratingChange())
                .build();
    }
}
