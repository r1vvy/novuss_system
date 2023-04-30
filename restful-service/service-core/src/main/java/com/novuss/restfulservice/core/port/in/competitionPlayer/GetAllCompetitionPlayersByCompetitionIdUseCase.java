package com.novuss.restfulservice.core.port.in.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;

import java.util.List;

public interface GetAllCompetitionPlayersByCompetitionIdUseCase {
    List<CompetitionPlayer> getAllByCompetitionId(String competitionId);
}
