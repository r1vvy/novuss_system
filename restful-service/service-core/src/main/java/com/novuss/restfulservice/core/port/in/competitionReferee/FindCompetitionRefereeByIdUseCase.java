package com.novuss.restfulservice.core.port.in.competitionReferee;

import com.novuss.restfulservice.domain.CompetitionReferee;

public interface FindCompetitionRefereeByIdUseCase {
    CompetitionReferee findById(String competitionId, String refereeId);
}
