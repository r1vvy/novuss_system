package com.novuss.restfulservice.core.port.in.competition;

import com.novuss.restfulservice.domain.Competition;

import java.util.List;

public interface FindAllCompetitionsUseCase {
    List<Competition> findAll();
}
