package com.novuss.restfulservice.core.service.competition_referee;

import com.novuss.restfulservice.core.port.in.competition_referee.DeleteCompetitionRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_referee.DeleteCompetitionRefereeByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCompetitionRefereeByIdService implements DeleteCompetitionRefereeByIdUseCase {
    private final DeleteCompetitionRefereeByIdPort deleteCompetitionRefereeByIdPort;
    @Override
    public void delete(String competitionId, String refereeId) {
        deleteCompetitionRefereeByIdPort.delete(competitionId, refereeId);
    }
}
