package com.novuss.restfulservice.core.port.in.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface UpdateCompetitionPlayerByIdUseCase {
    CompetitionPlayer updateById(CompetitionPlayer competitionPlayer, String playerId, String competitionId);
}
