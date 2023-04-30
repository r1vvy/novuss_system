package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.Competition;

public interface FindCompetitionByIdPort {
    Competition findById(String id);
}
