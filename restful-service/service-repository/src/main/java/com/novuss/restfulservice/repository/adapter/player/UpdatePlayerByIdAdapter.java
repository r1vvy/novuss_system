package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.port.out.player.UpdatePlayerByIdPort;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.converter.PlayerDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerByIdAdapter implements UpdatePlayerByIdPort {
    private final PlayerJpaRepository playerJpaRepository;
    @Override
    public Player updatePlayerById(Player player, String id) {
        var uuid = UUID.fromString(id);

        var oldEntity = playerJpaRepository.findById(uuid)
                .orElseThrow(
                        () -> new RuntimeException("Player with id " + id + " not found")
                );
        var updatedEntity = PlayerDomainToEntityConverter.convert(player);
        updatedEntity.setId(oldEntity.getId());
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        var updatedPlayer = playerJpaRepository.save(updatedEntity);

        return PlayerEntityToDomainConverter.convert(updatedPlayer);
    }
}
