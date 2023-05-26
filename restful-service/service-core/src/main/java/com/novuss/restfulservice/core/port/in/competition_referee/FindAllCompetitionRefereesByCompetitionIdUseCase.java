package com.novuss.restfulservice.core.port.in.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;

import java.util.List;

public interface FindAllCompetitionRefereesByCompetitionIdUseCase {
    List<CompetitionReferee> findAllByCompetitionId(String competitionId);
}
