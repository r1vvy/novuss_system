package com.novuss.restfulservice.core.service.competition_category;

import com.novuss.restfulservice.core.port.in.competition_category.FindCompetitionCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_category.FindCompetitionCategoryByIdPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionCategoryByIdService implements FindCompetitionCategoryByIdUseCase {
    private final FindCompetitionCategoryByIdPort findCompetitionCategoryByPort;
    @Override
    public CompetitionCategory findById(String id) {
        return findCompetitionCategoryByPort.findById(id);
    }
}
