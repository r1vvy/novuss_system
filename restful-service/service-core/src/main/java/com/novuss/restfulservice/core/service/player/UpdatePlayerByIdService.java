package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.port.in.player.UpdatePlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerByIdPort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerByIdService implements UpdatePlayerByIdUseCase {
    private final UpdatePlayerByIdPort updatePlayerByIdPort;
    @Override
    public Player updatePlayerById(Player player, String id) {
        return updatePlayerByIdPort.updatePlayerById(player, id);
    }
}
