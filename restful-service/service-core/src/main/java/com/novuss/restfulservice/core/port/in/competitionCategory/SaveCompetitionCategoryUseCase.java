package com.novuss.restfulservice.core.port.in.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface SaveCompetitionCategoryUseCase {
    CompetitionCategory save(CompetitionCategory competitionCategory);
}
