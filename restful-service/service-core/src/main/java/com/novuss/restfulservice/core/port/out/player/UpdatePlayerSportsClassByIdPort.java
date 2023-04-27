package com.novuss.restfulservice.core.port.out.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerSportsClassByIdPort {
    Player updatePlayerSportsClassById(String playerId, String sportsClassId);
}
