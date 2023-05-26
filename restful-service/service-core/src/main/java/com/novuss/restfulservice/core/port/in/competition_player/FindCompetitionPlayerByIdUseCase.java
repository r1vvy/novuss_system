package com.novuss.restfulservice.core.port.in.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface FindCompetitionPlayerByIdUseCase {
    CompetitionPlayer findById(String competitionId, String playerId);
}
