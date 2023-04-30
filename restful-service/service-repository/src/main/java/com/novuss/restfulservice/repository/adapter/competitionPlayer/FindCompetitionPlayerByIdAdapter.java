package com.novuss.restfulservice.repository.adapter.competitionPlayer;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competitionPlayer.FindCompetitionPlayerByIdPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
import com.novuss.restfulservice.repository.converter.CompetitionPlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionPlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionPlayerByIdAdapter implements FindCompetitionPlayerByIdPort {
    private final CompetitionPlayerJpaRepository competitionPlayerJpaRepository;
    @Override
    public CompetitionPlayer findById(String competitionId, String playerId) {
        var competitionUUID = UUID.fromString(competitionId);
        var playerUUID = UUID.fromString(playerId);
        var key = CompetitionPlayerCompositeKey.builder()
                .competitionEntityId(competitionUUID)
                .playerEntityId(playerUUID)
                .build();

        var competitionPlayer = competitionPlayerJpaRepository.findById(key)
                .orElseThrow(
                        () -> {
                            log.warn("Competition player with competition id {} and player id {} not found", competitionId, playerId);
                            throw new EntityNotFoundException("Competition player with competition id " + competitionId + " and player id " + playerId + " not found");
                        }
                );

        return CompetitionPlayerEntityToDomainConverter.convert(competitionPlayer);
    }
}
