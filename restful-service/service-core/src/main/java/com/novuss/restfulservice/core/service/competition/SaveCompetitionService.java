package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.SaveCompetitionUseCase;
import com.novuss.restfulservice.core.port.out.competition.SaveCompetitionPort;
import com.novuss.restfulservice.domain.Competition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionService implements SaveCompetitionUseCase {
    private final SaveCompetitionPort saveCompetitionPort;
    @Override
    public Competition save(Competition competition) {
        return saveCompetitionPort.save(competition);
    }
}
