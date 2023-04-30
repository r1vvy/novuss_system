package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.FindAllCompetitionsUseCase;
import com.novuss.restfulservice.core.port.out.competition.FindAllCompetitionsPort;
import com.novuss.restfulservice.domain.Competition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllCompetitionsService implements FindAllCompetitionsUseCase {
    private final FindAllCompetitionsPort findAllCompetitionsPort;
    @Override
    public List<Competition> findAll() {
        return findAllCompetitionsPort.findAll();
    }
}
