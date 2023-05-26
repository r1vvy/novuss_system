package com.novuss.restfulservice.core.port.in.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface SaveCompetitionPlayerUseCase {
    CompetitionPlayer save(String competitionId, CompetitionPlayer competitionPlayer);
}
