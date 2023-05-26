package com.novuss.restfulservice.core.service.competition_category;

import com.novuss.restfulservice.core.port.in.competition_category.UpdateCompetitionCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_category.UpdateCompetitionCategoryByIdPort;
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
