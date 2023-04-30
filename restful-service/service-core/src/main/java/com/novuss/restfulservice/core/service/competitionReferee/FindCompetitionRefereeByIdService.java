package com.novuss.restfulservice.core.service.competitionReferee;

import com.novuss.restfulservice.core.port.in.competitionReferee.FindCompetitionRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionReferee.FindCompetitionRefereeByIdPort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionRefereeByIdService implements FindCompetitionRefereeByIdUseCase {
    private final FindCompetitionRefereeByIdPort findCompetitionRefereeByIdPort;
    @Override
    public CompetitionReferee findById(String competitionId, String refereeId) {
        return findCompetitionRefereeByIdPort.findById(competitionId, refereeId);
    }
}
