package com.novuss.restfulservice.core.port.out.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;

public interface SaveCompetitionCategoryPort {
    CompetitionCategory save(CompetitionCategory competitionCategory);
}
