package com.novuss.restfulservice.core.port.out.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;

public interface SaveCompetitionPlayerPort {
    CompetitionPlayer save(CompetitionPlayer competitionPlayer, String competitionId);
}
