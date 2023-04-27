package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.port.in.player.UpdatePlayerLicenceByIdUseCase;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerLicenceByIdPort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerLicenceByIdService implements UpdatePlayerLicenceByIdUseCase {
    private final UpdatePlayerLicenceByIdPort updatePlayerLicenceByIdPort;
    @Override
    public Player updatePlayerLicenceById(String playerId, String licenceId) {
        return updatePlayerLicenceByIdPort.updatePlayerLicenceById(playerId, licenceId);
    }
}
