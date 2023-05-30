package com.novuss.restfulservice.core.port.out.competition;

import com.novuss.restfulservice.domain.FileDomain;

import java.util.List;

public interface GetCompetitionFilesByCompIdPort {
    List<FileDomain> getCompetitionFilesByCompId(String id);
}
