package com.novuss.restfulservice.core.port.in.player;

import com.restfulservice.domain.Player;

public interface SavePlayerUseCase {
    Player save(Player player);
}
