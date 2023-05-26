package com.novuss.restfulservice.repository.adapter.competition_player;

import com.novuss.restfulservice.core.port.out.competition_player.GetAllCompetitionPlayersByCompetitionIdPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import com.novuss.restfulservice.repository.converter.CompetitionPlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionPlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCompetitionPlayersByCompetitionIdAdapter implements GetAllCompetitionPlayersByCompetitionIdPort {
    private final CompetitionPlayerJpaRepository competitionPlayerJpaRepository;
    @Override
    public List<CompetitionPlayer> getAllByCompetitionId(String competitionId) {
        var competitionPlayerUUID = UUID.fromString(competitionId);
        var players = competitionPlayerJpaRepository.findAllByCompetitionId(competitionPlayerUUID);

        return players.stream()
                .map(CompetitionPlayerEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
