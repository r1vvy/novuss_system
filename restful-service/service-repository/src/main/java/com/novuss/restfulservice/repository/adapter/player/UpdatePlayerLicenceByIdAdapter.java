package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerLicenceByIdPort;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.LicenceEntity;
import com.novuss.restfulservice.repository.repository.jpa.LicenceJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdatePlayerLicenceByIdAdapter implements UpdatePlayerLicenceByIdPort {
    private final PlayerJpaRepository playerJpaRepository;
    private final LicenceJpaRepository licenceJpaRepository;
    @Override
    public Player updatePlayerLicenceById(String playerId, String licenceId) {
        var playerUUID = UUID.fromString(playerId);
        var licenceUUID = licenceId != null ? UUID.fromString(licenceId) : null;

        var playerEntity = playerJpaRepository.findById(playerUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Player with id {} not found", playerId);
                            return new EntityNotFoundException("Player not found with id " + playerId);
                        }
                );
        LicenceEntity licenceEntity = null;
        if(licenceUUID != null) {
            licenceEntity = licenceJpaRepository.findById(licenceUUID)
                    .orElseThrow(
                            () -> {
                                log.warn("Licence with id {} not found", licenceId);
                                return new EntityNotFoundException("Licence not found with id " + licenceId);
                            }
                    );
        }

        playerEntity.setLicenceEntity(licenceEntity);
        var updatedPlayerEntity = playerJpaRepository.save(playerEntity);

        return PlayerEntityToDomainConverter.convert(updatedPlayerEntity);
    }
}
