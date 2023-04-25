package com.novuss.restfulservice.core.port.in.player;

import com.novuss.restfulservice.domain.Player;

import java.util.List;

public interface GetAllPlayersUseCase {
    List<Player> getAll();
}
