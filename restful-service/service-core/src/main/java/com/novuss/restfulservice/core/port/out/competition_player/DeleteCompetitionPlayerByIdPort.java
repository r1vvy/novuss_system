package com.novuss.restfulservice.core.port.out.competition_player;

public interface DeleteCompetitionPlayerByIdPort {
    void deleteById(String competitionId, String playerId);
}
