package com.novuss.restfulservice.core.service.competitionPlayer;

import com.novuss.restfulservice.core.port.in.competitionPlayer.FindCompetitionPlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionPlayer.FindCompetitionPlayerByIdPort;
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
