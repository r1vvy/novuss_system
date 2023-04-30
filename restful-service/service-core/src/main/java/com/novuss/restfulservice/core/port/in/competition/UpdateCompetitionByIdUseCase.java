package com.novuss.restfulservice.core.port.in.competition;

import com.novuss.restfulservice.domain.Competition;

public interface UpdateCompetitionByIdUseCase {
    Competition update(String id, Competition competition);

    Competition updateCategoryById(String competitionId, String categoryId);
    Competition updateLocationById(String competitionId, String locationId);
}
