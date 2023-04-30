package com.novuss.restfulservice.repository.adapter.competitionPlayer;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competitionPlayer.SaveCompetitionPlayerPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
import com.novuss.restfulservice.repository.converter.CompetitionPlayerDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionPlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionPlayerJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionPlayerAdapter implements SaveCompetitionPlayerPort {
    private final CompetitionPlayerJpaRepository competitionPlayerJpaRepository;
    private final CompetitionJpaRepository competitionJpaRepository;
    private final PlayerJpaRepository playerJpaRepository;
    @Override
    public CompetitionPlayer save(CompetitionPlayer competitionPlayer, String competitionId) {
        playerJpaRepository.findById(competitionPlayer.playerId())
                .orElseThrow(() -> {
                    log.warn("Player with id {} not found", competitionPlayer.playerId());
                    throw new EntityNotFoundException("Player with id " + competitionPlayer.playerId() + " not found");
                });
        competitionJpaRepository.findById(UUID.fromString(competitionId))
                .orElseThrow(() -> {
                    log.warn("Competition with id {} not found", competitionId);
                    throw new EntityNotFoundException("Competition with id " + competitionId + " not found");
                });

        var key = CompetitionPlayerCompositeKey.builder()
                .competitionEntityId(UUID.fromString(competitionId))
                .playerEntityId(competitionPlayer.playerId())
                .build();
        competitionPlayerJpaRepository.findById(key).ifPresent(
                competitionPlayerEntity -> {
                    log.warn("Competition player with id {} already exists", key);
                    throw new EntityExistsException("Competition player with id " + key + " already exists");
                }
        );

        var competitionPlayerEntity = CompetitionPlayerDomainToEntityConverter.convert(competitionPlayer);
        competitionPlayerEntity.setId(key);
        var savedCompetitionPlayer = competitionPlayerJpaRepository.save(competitionPlayerEntity);

        return CompetitionPlayerEntityToDomainConverter.convert(savedCompetitionPlayer);
    }
}
