package com.novuss.restfulservice.core.port.in.competition;

import com.novuss.restfulservice.domain.FileDomain;

import java.util.List;

public interface GetCompetitionFilesByCompIdUseCase {
    List<FileDomain> getCompetitionFilesByCompId(String id);
}
