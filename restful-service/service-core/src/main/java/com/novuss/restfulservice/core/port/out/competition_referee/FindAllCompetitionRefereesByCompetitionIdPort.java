package com.novuss.restfulservice.core.port.out.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;

import java.util.List;

public interface FindAllCompetitionRefereesByCompetitionIdPort {
    List<CompetitionReferee> findAllByCompetitionId(String competitionId);
}
