package com.novuss.restfulservice.core.service.competition_player;

import com.novuss.restfulservice.core.port.in.competition_player.FindCompetitionPlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_player.FindCompetitionPlayerByIdPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionPlayerByIdService implements FindCompetitionPlayerByIdUseCase {
    private final FindCompetitionPlayerByIdPort findCompetitionPlayerByIdPort;

    @Override
    public CompetitionPlayer findById(String competitionId, String playerId) {
        return findCompetitionPlayerByIdPort.findById(competitionId, playerId);
    }
}
