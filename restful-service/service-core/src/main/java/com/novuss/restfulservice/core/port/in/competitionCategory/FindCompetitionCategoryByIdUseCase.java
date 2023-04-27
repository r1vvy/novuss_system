package com.novuss.restfulservice.core.port.in.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface FindCompetitionCategoryByIdUseCase {
    CompetitionCategory findById(String id);
}
