package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.port.in.player.UpdatePlayerSportsClassByIdUseCase;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerSportsClassByIdPort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerSportsClassByIdService implements UpdatePlayerSportsClassByIdUseCase {
    private final UpdatePlayerSportsClassByIdPort updatePlayerSportsClassByIdPort;
    @Override
    public Player updatePlayerSportsClassById(String playerId, String sportsClassId) {
        return updatePlayerSportsClassByIdPort.updatePlayerSportsClassById(playerId, sportsClassId);
    }
}
