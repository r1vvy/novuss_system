package com.novuss.restfulservice.core.port.out.competitionReferee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface SaveCompetitionRefereePort {
    CompetitionReferee save(String competitionId,
                            String refereeId,
                            CompetitionReferee competitionReferee
    );
}