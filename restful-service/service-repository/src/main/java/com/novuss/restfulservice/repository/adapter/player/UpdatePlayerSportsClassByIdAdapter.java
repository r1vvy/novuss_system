package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerSportsClassByIdPort;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.SportsClassEntity;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.SportsClassJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerSportsClassByIdAdapter implements UpdatePlayerSportsClassByIdPort {
    private final PlayerJpaRepository playerJpaRepository;
    private final SportsClassJpaRepository sportsClassJpaRepository;
    @Override
    public Player updatePlayerSportsClassById(String playerId, String sportsClassId) {
        var playerUUID = UUID.fromString(playerId);
        var sportsClassUUID = sportsClassId != null ? UUID.fromString(sportsClassId) : null;

        var playerEntity = playerJpaRepository.findById(playerUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Player with id {} not found", playerId);
                            return new EntityNotFoundException("Player not found with id " + playerId);
                        }
                );
        SportsClassEntity sportsClassEntity = null;
        if(sportsClassUUID != null) {
            sportsClassEntity = sportsClassJpaRepository.findById(sportsClassUUID)
                    .orElseThrow(
                            () -> {
                                log.warn("SportsClass with id {} not found", sportsClassId);
                                return new EntityNotFoundException("SportsClass not found with id " + sportsClassId);
                            }
                    );
        }

        playerEntity.setSportsClassEntity(sportsClassEntity);
        var updatedPlayerEntity = playerJpaRepository.save(playerEntity);

        return PlayerEntityToDomainConverter.convert(updatedPlayerEntity);
    }
}
