package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.Competition;

public interface UpdateCompetitionByIdPort {
    Competition update(String id, Competition competition);
}
