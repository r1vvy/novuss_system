package com.novuss.restfulservice.core.port.in.competitionPlayer;

public interface DeleteCompetitionPlayerByIdUseCase {
    void deleteById(String competitionId, String playerId);
}
