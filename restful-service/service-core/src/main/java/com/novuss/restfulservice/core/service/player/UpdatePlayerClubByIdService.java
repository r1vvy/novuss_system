package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.port.in.player.UpdatePlayerClubByIdUseCase;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerClubByIdPort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerClubByIdService implements UpdatePlayerClubByIdUseCase {
    private final UpdatePlayerClubByIdPort updatePlayerClubByIdPort;
    @Override
    public Player updatePlayerClubById(String playerId, String clubId) {
        return updatePlayerClubByIdPort.updatePlayerClubById(playerId, clubId);
    }
}
