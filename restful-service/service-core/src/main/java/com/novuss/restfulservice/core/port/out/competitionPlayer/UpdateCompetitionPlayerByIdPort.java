package com.novuss.restfulservice.core.port.out.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface UpdateCompetitionPlayerByIdPort {
    CompetitionPlayer updateById(String playerId, String competitionId, CompetitionPlayer competitionPlayer);
}
