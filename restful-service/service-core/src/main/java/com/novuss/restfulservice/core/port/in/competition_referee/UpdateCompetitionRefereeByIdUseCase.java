package com.novuss.restfulservice.core.port.in.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface UpdateCompetitionRefereeByIdUseCase {
    CompetitionReferee update(String competitionId,
                              String refereeId,
                              CompetitionReferee competitionReferee
    );
}
