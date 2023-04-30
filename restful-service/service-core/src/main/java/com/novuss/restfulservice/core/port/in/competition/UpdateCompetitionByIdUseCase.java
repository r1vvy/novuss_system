package com.novuss.restfulservice.core.port.in.competition;

import com.novuss.restfulservice.domain.Competition;

public interface UpdateCompetitionByIdUseCase {
    Competition update(String id, Competition competition);
}
