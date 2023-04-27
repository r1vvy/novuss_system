package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerClubByIdPort;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.ClubEntity;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerClubByIdAdapter implements UpdatePlayerClubByIdPort {
    private final PlayerJpaRepository playerJpaRepository;
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public Player updatePlayerClubById(String playerId, String clubId) {
        var playerUUID = UUID.fromString(playerId);
        var clubUUID = clubId != null ? UUID.fromString(clubId) : null;

        var playerEntity = playerJpaRepository.findById(playerUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Player with id {} not found", playerId);
                            return new EntityNotFoundException("Player not found with id " + playerId);
                        }
                );
        ClubEntity clubEntity = null;
        if(clubUUID != null) {
            clubEntity = clubJpaRepository.findById(clubUUID)
                    .orElseThrow(
                            () -> {
                                log.warn("Club with id {} not found", clubId);
                                return new EntityNotFoundException("Club not found with id " + clubId);
                            }
                    );
        }

        playerEntity.setClubEntity(clubEntity);
        var updatedPlayerEntity = playerJpaRepository.save(playerEntity);

        return PlayerEntityToDomainConverter.convert(updatedPlayerEntity);
    }
}
