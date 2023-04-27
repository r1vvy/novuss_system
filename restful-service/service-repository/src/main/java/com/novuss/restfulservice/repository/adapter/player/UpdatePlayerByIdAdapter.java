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
        var playerId = UUID.fromString(id);

        var oldEntity = playerJpaRepository.findById(playerId)
                .orElseThrow(
                        () -> new RuntimeException("Player with id " + id + " not found")
                );

        var updatedEntity = PlayerDomainToEntityConverter.convert(player);
        updatedEntity.setId(oldEntity.getId());
        if(oldEntity.getClubEntity() != null) {
            updatedEntity.setClubEntity(oldEntity.getClubEntity());
        }
        if(oldEntity.getLicenceEntity() != null) {
            updatedEntity.setLicenceEntity(oldEntity.getLicenceEntity());
        }
        if (oldEntity.getSportsClassEntity() != null) {
            updatedEntity.setSportsClassEntity(oldEntity.getSportsClassEntity());
        }
        updatedEntity.setPersonEntity(oldEntity.getPersonEntity());
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        var updatedPlayer = playerJpaRepository.save(updatedEntity);

        return PlayerEntityToDomainConverter.convert(updatedPlayer);
    }
}
