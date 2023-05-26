package com.novuss.restfulservice.core.port.out.competition_referee;

public interface DeleteCompetitionRefereeByIdPort {
    void delete(String competitionId, String refereeId);
}
