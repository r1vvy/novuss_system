package com.novuss.restfulservice.core.port.out.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface UpdateCompetitionRefereeByIdPort {
    CompetitionReferee update(String competitionId,
                              String refereeId,
                              CompetitionReferee competitionReferee
    );
}
