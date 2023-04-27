package com.novuss.restfulservice.core.service.competitionCategory;

import com.novuss.restfulservice.core.port.in.competitionCategory.UpdateCompetitionCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionCategory.UpdateCompetitionCategoryByIdPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionCategoryByIdService implements UpdateCompetitionCategoryByIdUseCase {
    private final UpdateCompetitionCategoryByIdPort updateCompetitionCategoryByIdPort;
    @Override
    public CompetitionCategory updateById(CompetitionCategory competitionCategory, String id) {
        return updateCompetitionCategoryByIdPort.updateById(competitionCategory, id);
    }
}
