package com.novuss.restfulservice.core.port.in.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;

import java.util.List;

public interface GetAllCompetitionCategoriesUseCase {
    List<CompetitionCategory> getAll();
}
