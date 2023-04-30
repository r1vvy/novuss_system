package com.novuss.restfulservice.core.port.out.competitionPlayer;

import com.novuss.restfulservice.domain.CompetitionPlayer;

import java.util.List;

public interface GetAllCompetitionPlayersByCompetitionIdPort {
    List<CompetitionPlayer> getAllByCompetitionId(String competitionId);
}
