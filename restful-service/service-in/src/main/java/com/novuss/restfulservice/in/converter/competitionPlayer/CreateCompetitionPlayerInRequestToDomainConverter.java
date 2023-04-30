package com.novuss.restfulservice.in.converter.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionPlayerInRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCompetitionPlayerInRequestToDomainConverter {
    public static CompetitionPlayer convert(CreateCompetitionPlayerInRequest request) {
        return CompetitionPlayer.builder()
                .placement(request.placement())
                .ratingChange(request.ratingChange())
                .build();
    }
}
