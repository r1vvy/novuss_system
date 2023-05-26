package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.UpdateCompetitionByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition.AddFileForCompetitionPort;
import com.novuss.restfulservice.core.port.out.competition.UpdateCategoryForCompetitionPort;
import com.novuss.restfulservice.core.port.out.competition.UpdateCompetitionByIdPort;
import com.novuss.restfulservice.core.port.out.competition.UpdateLocationForCompetitionPort;
import com.novuss.restfulservice.domain.Competition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionByIdService implements UpdateCompetitionByIdUseCase {
    private final UpdateCompetitionByIdPort updateCompetitionByIdPort;
    private final UpdateCategoryForCompetitionPort updateCategoryForCompetitionPort;
    private final UpdateLocationForCompetitionPort updateLocationForCompetitionPort;
    private final AddFileForCompetitionPort addFileForCompetitionPort;
    @Override
    public Competition update(String id, Competition competition) {
        return updateCompetitionByIdPort.update(id, competition);
    }

    @Override
    public Competition updateCategoryById(String competitionId, String categoryId) {
        return updateCategoryForCompetitionPort.update(competitionId, categoryId);
    }

    @Override
    public Competition updateLocationById(String competitionId, String locationId) {
        return updateLocationForCompetitionPort.update(competitionId, locationId);
    }

    @Override
    public void addFileById(String competitionId, String fileId) {
        addFileForCompetitionPort.addFileById(competitionId, fileId);
    }
}
