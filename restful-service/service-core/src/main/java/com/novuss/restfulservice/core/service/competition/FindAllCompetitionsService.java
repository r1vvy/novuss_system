package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.FindAllCompetitionsUseCase;
import com.novuss.restfulservice.core.port.out.competition.FindAllCompetitionsPort;
import com.novuss.restfulservice.domain.Competition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllCompetitionsService implements FindAllCompetitionsUseCase {
    private final FindAllCompetitionsPort findAllCompetitionsPort;
    @Override
    public Page<Competition> findAllByPage(Pageable pageable) {
        return findAllCompetitionsPort.findAllByPage(pageable);
    }
}
