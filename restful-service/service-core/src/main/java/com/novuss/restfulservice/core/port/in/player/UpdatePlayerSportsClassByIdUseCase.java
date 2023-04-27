package com.novuss.restfulservice.core.port.in.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerSportsClassByIdUseCase {
    Player updatePlayerSportsClassById(String playerId, String sportsClassId);
}
