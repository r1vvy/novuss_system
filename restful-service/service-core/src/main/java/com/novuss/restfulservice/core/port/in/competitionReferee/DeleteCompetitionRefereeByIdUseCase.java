package com.novuss.restfulservice.core.port.in.competitionReferee;

public interface DeleteCompetitionRefereeByIdUseCase {
    void delete(String competitionId, String refereeId);
}
