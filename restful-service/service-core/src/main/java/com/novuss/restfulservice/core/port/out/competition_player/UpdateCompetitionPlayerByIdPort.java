package com.novuss.restfulservice.core.port.out.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface UpdateCompetitionPlayerByIdPort {
    CompetitionPlayer updateById(String playerId, String competitionId, CompetitionPlayer competitionPlayer);
}
