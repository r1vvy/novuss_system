package com.novuss.restfulservice.core.port.out.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface FindCompetitionPlayerByIdPort {
    CompetitionPlayer findById(String competitionId, String playerId);
}
