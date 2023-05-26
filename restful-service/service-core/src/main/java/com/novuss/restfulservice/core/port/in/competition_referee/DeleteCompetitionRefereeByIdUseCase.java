package com.novuss.restfulservice.core.port.in.competition_referee;

public interface DeleteCompetitionRefereeByIdUseCase {
    void delete(String competitionId, String refereeId);
}
