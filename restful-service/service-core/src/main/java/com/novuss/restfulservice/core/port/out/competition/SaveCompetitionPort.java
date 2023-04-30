package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.domain.CompetitionReferee;

public interface SaveCompetitionPort {
    Competition save(Competition competition);
}
