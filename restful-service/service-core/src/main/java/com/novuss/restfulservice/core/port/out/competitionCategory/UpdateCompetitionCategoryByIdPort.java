package com.novuss.restfulservice.core.port.out.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface UpdateCompetitionCategoryByIdPort {
    CompetitionCategory updateById(CompetitionCategory competitionCategory, String id);
}
