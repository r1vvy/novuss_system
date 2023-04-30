package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.UpdateCompetitionByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition.UpdateCompetitionByIdPort;
import com.novuss.restfulservice.domain.Competition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionByIdService implements UpdateCompetitionByIdUseCase {
    private final UpdateCompetitionByIdPort updateCompetitionByIdPort;
    @Override
    public Competition update(String id, Competition competition) {
        return updateCompetitionByIdPort.update(id, competition);
    }
}
