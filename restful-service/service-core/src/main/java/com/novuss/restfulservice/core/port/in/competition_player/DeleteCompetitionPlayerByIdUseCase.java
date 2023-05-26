package com.novuss.restfulservice.core.port.in.competition_player;

public interface DeleteCompetitionPlayerByIdUseCase {
    void deleteById(String competitionId, String playerId);
}
