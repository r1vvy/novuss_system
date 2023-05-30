package com.novuss.restfulservice.core.port.in.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface SaveCompetitionRefereeUseCase {
    CompetitionReferee save(CompetitionReferee competitionReferee);
}
