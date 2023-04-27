package com.novuss.restfulservice.core.port.in.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerLicenceByIdUseCase {
    Player updatePlayerLicenceById(String playerId, String licenceId);
}
