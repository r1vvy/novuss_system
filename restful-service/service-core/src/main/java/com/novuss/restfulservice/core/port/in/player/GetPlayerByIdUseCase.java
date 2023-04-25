package com.novuss.restfulservice.core.port.in.player;

import com.novuss.restfulservice.domain.Player;

public interface GetPlayerByIdUseCase {
    Player getById(String id);
}
