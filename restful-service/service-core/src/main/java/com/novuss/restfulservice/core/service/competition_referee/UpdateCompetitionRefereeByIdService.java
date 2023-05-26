package com.novuss.restfulservice.core.service.competition_referee;

import com.novuss.restfulservice.core.port.in.competition_referee.UpdateCompetitionRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_referee.UpdateCompetitionRefereeByIdPort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionRefereeByIdService implements UpdateCompetitionRefereeByIdUseCase {
    private final UpdateCompetitionRefereeByIdPort updateCompetitionRefereeByIdPort;
    @Override
    public CompetitionReferee update(String competitionId,
                                     String refereeId,
                                     CompetitionReferee competitionReferee)
    {
        return updateCompetitionRefereeByIdPort.update(
                competitionId,
                refereeId,
                competitionReferee
        );
    }
}
