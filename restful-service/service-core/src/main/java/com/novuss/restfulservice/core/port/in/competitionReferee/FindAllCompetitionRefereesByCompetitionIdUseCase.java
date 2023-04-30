package com.novuss.restfulservice.core.port.in.competitionReferee;

import com.novuss.restfulservice.domain.CompetitionReferee;

import java.util.List;

public interface FindAllCompetitionRefereesByCompetitionIdUseCase {
    List<CompetitionReferee> findAllByCompetitionId(String competitionId);
}
