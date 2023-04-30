package com.novuss.restfulservice.core.port.out.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface FindCompetitionPlayerByIdPort {
    CompetitionPlayer findById(String competitionId, String playerId);
}
