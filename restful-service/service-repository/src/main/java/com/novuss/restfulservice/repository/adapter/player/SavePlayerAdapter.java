package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.port.out.SavePlayerPort;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavePlayerAdapter implements SavePlayerPort {
    private final PlayerJpaRepository playerJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public Player save(Player player) {
        var playerEntity = mapStructMapper.playerDomainToEntity(player);
        var savedPlayerEntity = playerJpaRepository.save(playerEntity);

        return mapStructMapper.playerEntityToDomain(savedPlayerEntity);
    }
}
