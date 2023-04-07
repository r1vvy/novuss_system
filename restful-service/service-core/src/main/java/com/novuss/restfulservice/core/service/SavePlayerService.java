package com.novuss.restfulservice.core.service;

import com.novuss.restfulservice.core.port.in.player.SavePlayerUseCase;
import com.novuss.restfulservice.core.port.out.SavePlayerPort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePlayerService implements SavePlayerUseCase {
    private final SavePlayerPort savePlayerPort;

    @Override
    public Player save(Player player) {
        return savePlayerPort.save(player);
    }
}
