package com.novuss.restfulservice.in.util.converter.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionPlayerInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompetitionPlayerInRequestToDomainConverter {
    public static CompetitionPlayer convert(UpdateCompetitionPlayerInRequest request) {
        return CompetitionPlayer.builder()
                .placement(request.placement())
                .ratingChange(request.ratingChange())
                .build();
    }
}
