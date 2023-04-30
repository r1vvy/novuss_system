package com.novuss.restfulservice.core.service.competitionReferee;

import com.novuss.restfulservice.core.port.in.competitionReferee.UpdateCompetitionRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionReferee.UpdateCompetitionRefereeByIdPort;
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
