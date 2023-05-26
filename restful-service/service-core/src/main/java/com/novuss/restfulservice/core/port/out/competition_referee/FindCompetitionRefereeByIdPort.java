package com.novuss.restfulservice.core.port.out.competition_referee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface FindCompetitionRefereeByIdPort {
    CompetitionReferee findById(String competitionId, String refereeId);
}
