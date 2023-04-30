package com.novuss.restfulservice.core.service.competitionReferee;

import com.novuss.restfulservice.core.port.in.competitionReferee.SaveCompetitionRefereeUseCase;
import com.novuss.restfulservice.core.port.out.competitionReferee.SaveCompetitionRefereePort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionRefereeService implements SaveCompetitionRefereeUseCase {
    private final SaveCompetitionRefereePort saveCompetitionRefereePort;
    @Override
    public CompetitionReferee save(String competitionId, String refereeId, CompetitionReferee competitionReferee) {
        return saveCompetitionRefereePort.save(competitionId, refereeId, competitionReferee);
    }
}
