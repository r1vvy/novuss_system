package com.novuss.restfulservice.core.port.out.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface SaveCompetitionRefereePort {
    CompetitionReferee save(CompetitionReferee competitionReferee);
}
