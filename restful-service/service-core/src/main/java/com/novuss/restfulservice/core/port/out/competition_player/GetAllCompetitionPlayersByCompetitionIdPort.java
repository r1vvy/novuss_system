package com.novuss.restfulservice.core.port.out.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;

import java.util.List;

public interface GetAllCompetitionPlayersByCompetitionIdPort {
    List<CompetitionPlayer> getAllByCompetitionId(String competitionId);
}
