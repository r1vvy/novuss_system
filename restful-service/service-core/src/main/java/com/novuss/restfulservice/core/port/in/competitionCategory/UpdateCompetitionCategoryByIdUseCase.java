package com.novuss.restfulservice.core.port.in.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface UpdateCompetitionCategoryByIdUseCase {
    CompetitionCategory updateById(CompetitionCategory competitionCategory, String id);
}
