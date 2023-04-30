package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.Competition;

public interface UpdateLocationForCompetitionPort {
    Competition update(String competitionId, String locationId);
}
