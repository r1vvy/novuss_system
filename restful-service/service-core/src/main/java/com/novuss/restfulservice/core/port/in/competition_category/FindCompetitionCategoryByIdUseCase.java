package com.novuss.restfulservice.core.port.in.competition_category;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface FindCompetitionCategoryByIdUseCase {
    CompetitionCategory findById(String id);
}
