package com.novuss.restfulservice.core.port.in.competition;

import com.novuss.restfulservice.domain.Competition;

public interface SaveCompetitionUseCase {
    Competition save(Competition competition);
}
