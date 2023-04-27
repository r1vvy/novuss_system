package com.novuss.restfulservice.core.port.in.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerClubByIdUseCase {
    Player updatePlayerClubById(String playerId, String clubId);
}
