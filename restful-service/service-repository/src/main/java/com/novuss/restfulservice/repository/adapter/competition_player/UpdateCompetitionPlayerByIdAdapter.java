package com.novuss.restfulservice.repository.adapter.competition_player;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition_player.UpdateCompetitionPlayerByIdPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
import com.novuss.restfulservice.repository.converter.CompetitionPlayerDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionPlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionPlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionPlayerByIdAdapter implements UpdateCompetitionPlayerByIdPort {
    private final CompetitionPlayerJpaRepository competitionPlayerJpaRepository;
    @Override
    public CompetitionPlayer updateById(String playerId, String competitionId, CompetitionPlayer competitionPlayer) {
        var key = CompetitionPlayerCompositeKey.builder()
                .playerEntityId(UUID.fromString(playerId))
                .competitionEntityId(UUID.fromString(competitionId))
                .build();
        var oldEntity = competitionPlayerJpaRepository.findById(key)
                .orElseThrow(() -> {
                        log.warn("Competition player with id {} not found", key);
                        throw new EntityNotFoundException("Competition player not found with id " + key);
                });
        var updatedEntity = CompetitionPlayerDomainToEntityConverter.convert(competitionPlayer);
        updatedEntity.setId(key);
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        var savedEntity = competitionPlayerJpaRepository.save(updatedEntity);
        return CompetitionPlayerEntityToDomainConverter.convert(savedEntity);
    }
}
