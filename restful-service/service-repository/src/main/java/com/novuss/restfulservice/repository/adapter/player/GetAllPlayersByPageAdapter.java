package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.port.out.player.GetAllPlayersByPagePort;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllPlayersByPageAdapter implements GetAllPlayersByPagePort {
    private final PlayerJpaRepository playerJpaRepository;

    @Override
    public Page<Player> getAllPlayersByPage(Pageable pageable) {
        log.info("Searching for players with page: {}", pageable);

        var page = playerJpaRepository.findAll(pageable);

        return page.map(PlayerEntityToDomainConverter::convert);
    }
}
