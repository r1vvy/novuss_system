package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.port.in.player.GetAllPlayersUseCase;
import com.novuss.restfulservice.core.port.out.player.GetAllPlayersPort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetAllPlayersService implements GetAllPlayersUseCase {
    private final GetAllPlayersPort getAllPlayersPort;
    @Override
    public List<Player> getAll() {
        return getAllPlayersPort.getAllPlayers();
    }
}
