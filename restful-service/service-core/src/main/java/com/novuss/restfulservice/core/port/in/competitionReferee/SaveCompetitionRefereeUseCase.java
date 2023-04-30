package com.novuss.restfulservice.core.port.in.competitionReferee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface SaveCompetitionRefereeUseCase {
    CompetitionReferee save(String competitionId, String refereeId, CompetitionReferee competitionReferee);
}
