package com.novuss.restfulservice.repository.adapter.competitionPlayer;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competitionPlayer.DeleteCompetitionPlayerByIdPort;
import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionPlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteCompetitionPlayerByIdAdapter implements DeleteCompetitionPlayerByIdPort {
    private final CompetitionPlayerJpaRepository competitionPlayerJpaRepository;
    @Override
    public void deleteById(String competitionId, String playerId) {
        var competitionUUID = UUID.fromString(competitionId);
        var playerUUID = UUID.fromString(playerId);

        var key = CompetitionPlayerCompositeKey.builder()
                .competitionEntityId(competitionUUID)
                .playerEntityId(playerUUID)
                .build();

        competitionPlayerJpaRepository.findById(key)
                .orElseThrow(
                        () -> {
                            log.warn("Competition player with competition id {} and player id {} not found", competitionId, playerId);
                            throw new EntityNotFoundException("Competition player with competition id " + competitionId + " and player id " + playerId + " not found");
                        }
                );

        competitionPlayerJpaRepository.deleteById(key);
    }
}
