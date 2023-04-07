package com.novuss.restfulservice.core.port.out;

import com.novuss.restfulservice.domain.Player;

public interface SavePlayerPort {

    Player save(Player player);
}
