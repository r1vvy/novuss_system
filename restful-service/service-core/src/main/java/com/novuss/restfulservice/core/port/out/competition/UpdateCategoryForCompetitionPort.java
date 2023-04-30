package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.Competition;

public interface UpdateCategoryForCompetitionPort {
    Competition update(String competitionId, String categoryId);
}
