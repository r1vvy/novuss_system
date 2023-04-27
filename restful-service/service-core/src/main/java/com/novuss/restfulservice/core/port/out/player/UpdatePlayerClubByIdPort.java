package com.novuss.restfulservice.core.port.out.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerClubByIdPort {
    Player updatePlayerClubById(String playerId, String clubId);
}
