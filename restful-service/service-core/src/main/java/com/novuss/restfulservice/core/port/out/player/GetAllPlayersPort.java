package com.novuss.restfulservice.core.port.out.player;

import com.novuss.restfulservice.domain.Player;

import java.util.List;

public interface GetAllPlayersPort {
    List<Player> getAllPlayers();
}
