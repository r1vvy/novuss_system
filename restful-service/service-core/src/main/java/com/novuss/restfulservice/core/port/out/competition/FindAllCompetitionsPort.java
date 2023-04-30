package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllCompetitionsPort {
    Page<Competition> findAllByPage(Pageable pageable);
}
