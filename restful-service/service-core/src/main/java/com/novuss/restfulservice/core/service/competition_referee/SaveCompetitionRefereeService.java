package com.novuss.restfulservice.core.service.competition_referee;

import com.novuss.restfulservice.core.port.in.competition_referee.SaveCompetitionRefereeUseCase;
import com.novuss.restfulservice.core.port.out.competition_referee.SaveCompetitionRefereePort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionRefereeService implements SaveCompetitionRefereeUseCase {
    private final SaveCompetitionRefereePort saveCompetitionRefereePort;
    @Override
    public CompetitionReferee save(CompetitionReferee competitionReferee) {
        return saveCompetitionRefereePort.save(competitionReferee);
    }
}
