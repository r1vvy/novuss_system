package com.novuss.restfulservice.core.port.in.competition_player;

import com.novuss.restfulservice.domain.CompetitionPlayer;

import java.util.List;

public interface GetAllCompetitionPlayersByCompetitionIdUseCase {
    List<CompetitionPlayer> getAllByCompetitionId(String competitionId);
}
