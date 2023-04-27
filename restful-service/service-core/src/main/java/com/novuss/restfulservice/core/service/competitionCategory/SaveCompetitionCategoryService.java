package com.novuss.restfulservice.core.service.competitionCategory;

import com.novuss.restfulservice.core.port.in.competitionCategory.SaveCompetitionCategoryUseCase;
import com.novuss.restfulservice.core.port.out.competitionCategory.SaveCompetitionCategoryPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionCategoryService implements SaveCompetitionCategoryUseCase {
    private final SaveCompetitionCategoryPort saveCompetitionCategoryPort;
    @Override
    public CompetitionCategory save(CompetitionCategory competitionCategory) {
        return saveCompetitionCategoryPort.save(competitionCategory);
    }
}
