package com.novuss.restfulservice.core.service.competition_referee;

import com.novuss.restfulservice.core.port.in.competition_referee.FindAllCompetitionRefereesByCompetitionIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_referee.FindAllCompetitionRefereesByCompetitionIdPort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllCompetitionRefereesByCompetitionIdService implements FindAllCompetitionRefereesByCompetitionIdUseCase {
    private final FindAllCompetitionRefereesByCompetitionIdPort findALlCompetitionRefereesByCompetitionIdPort;
    @Override
    public List<CompetitionReferee> findAllByCompetitionId(String competitionId) {
        return findALlCompetitionRefereesByCompetitionIdPort.findAllByCompetitionId(competitionId);
    }
}
