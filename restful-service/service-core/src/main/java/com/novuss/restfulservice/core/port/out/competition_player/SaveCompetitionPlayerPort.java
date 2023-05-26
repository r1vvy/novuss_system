package com.novuss.restfulservice.core.port.out.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface SaveCompetitionPlayerPort {
    CompetitionPlayer save(CompetitionPlayer competitionPlayer, String competitionId);
}
