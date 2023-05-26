package com.novuss.restfulservice.in.util.converter.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionPlayerInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateCompetitionPlayerInRequestToDomainConverter {
    public static CompetitionPlayer convert(CreateCompetitionPlayerInRequest request) {
        return CompetitionPlayer.builder()
                .placement(request.placement())
                .ratingChange(request.ratingChange())
                .build();
    }
}
