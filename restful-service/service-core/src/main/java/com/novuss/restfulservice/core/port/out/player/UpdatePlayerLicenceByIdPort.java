package com.novuss.restfulservice.core.port.out.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerLicenceByIdPort {
    Player updatePlayerLicenceById(String playerId, String licenceId);
}
