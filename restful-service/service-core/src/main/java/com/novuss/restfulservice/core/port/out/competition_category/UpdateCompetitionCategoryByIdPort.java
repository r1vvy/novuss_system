package com.novuss.restfulservice.core.port.out.competition_category;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface UpdateCompetitionCategoryByIdPort {
    CompetitionCategory updateById(CompetitionCategory competitionCategory, String id);
}
