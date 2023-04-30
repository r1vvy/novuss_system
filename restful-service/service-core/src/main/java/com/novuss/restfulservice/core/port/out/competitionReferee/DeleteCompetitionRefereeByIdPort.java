package com.novuss.restfulservice.core.port.out.competitionReferee;

public interface DeleteCompetitionRefereeByIdPort {
    void delete(String competitionId, String refereeId);
}
