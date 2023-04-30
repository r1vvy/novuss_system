package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.FindCompetitionByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition.FindCompetitionByIdPort;
import com.novuss.restfulservice.domain.Competition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionByIdService implements FindCompetitionByIdUseCase {
    private final FindCompetitionByIdPort findCompetitionByIdPort;
    @Override
    public Competition findById(String id) {
        return findCompetitionByIdPort.findById(id);
    }
}
