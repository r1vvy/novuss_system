package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.port.in.player.GetAllPlayersByPageUseCase;
import com.novuss.restfulservice.core.port.out.player.GetAllPlayersByPagePort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetAllPlayersByPageService implements GetAllPlayersByPageUseCase {
    private final GetAllPlayersByPagePort getAllPlayersByPagePort;

    @Override
    public Page<Player> getAllByPage(Pageable pageable) {
        log.info("Get all players by page = {}", pageable);

        return getAllPlayersByPagePort.getAllPlayersByPage(pageable);
    }
}
