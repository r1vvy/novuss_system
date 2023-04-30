package com.novuss.restfulservice.core.port.out.player;

import com.novuss.restfulservice.domain.Player;

public interface FindPlayerByIdPort {
    Player findById(String id);
}
