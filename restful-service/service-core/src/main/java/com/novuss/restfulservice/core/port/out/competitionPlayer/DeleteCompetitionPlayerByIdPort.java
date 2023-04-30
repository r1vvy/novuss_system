package com.novuss.restfulservice.core.port.out.competitionPlayer;

public interface DeleteCompetitionPlayerByIdPort {
    void deleteById(String competitionId, String playerId);
}
