package com.novuss.restfulservice.core.service.competitionPlayer;

import com.novuss.restfulservice.core.port.in.competitionPlayer.GetAllCompetitionPlayersByCompetitionIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionPlayer.GetAllCompetitionPlayersByCompetitionIdPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllCompetitionPlayersByCompetitionIdService implements GetAllCompetitionPlayersByCompetitionIdUseCase {
    private final GetAllCompetitionPlayersByCompetitionIdPort getAllCompetitionPlayersByCompetitionIdPort;

    @Override
    public List<CompetitionPlayer> getAllByCompetitionId(String competitionId) {
        return getAllCompetitionPlayersByCompetitionIdPort.getAllByCompetitionId(competitionId);
    }
}
