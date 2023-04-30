package com.novuss.restfulservice.core.port.in.competition;

import com.novuss.restfulservice.domain.Competition;

public interface FindCompetitionByIdUseCase {
    Competition findById(String id);
}
