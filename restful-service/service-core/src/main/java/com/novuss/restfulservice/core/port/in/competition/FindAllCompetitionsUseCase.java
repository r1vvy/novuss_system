package com.novuss.restfulservice.core.port.in.competition;

import com.novuss.restfulservice.domain.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllCompetitionsUseCase {
    Page<Competition> findAllByPage(Pageable pageable);
}
