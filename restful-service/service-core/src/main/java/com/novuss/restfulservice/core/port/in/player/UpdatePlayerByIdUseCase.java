package com.novuss.restfulservice.core.port.in.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerByIdUseCase {
    Player updatePlayerById(Player player, String id);
}
