package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.Competition;

import java.util.List;

public interface FindAllCompetitionsPort {
    List<Competition> findAll();
}
