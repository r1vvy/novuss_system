package com.novuss.restfulservice.core.port.out.competition_category;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface FindCompetitionCategoryByIdPort {
    CompetitionCategory findById(String id);
}
