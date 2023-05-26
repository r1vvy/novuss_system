package com.novuss.restfulservice.core.service.competition_category;

import com.novuss.restfulservice.core.port.in.competition_category.GetAllCompetitionCategoriesUseCase;
import com.novuss.restfulservice.core.port.out.competition_category.GetAllCompetitionCategoriesPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllCompetitionCategoriesService implements GetAllCompetitionCategoriesUseCase {
    private final GetAllCompetitionCategoriesPort getAllCompetitionCategoriesPort;
    @Override
    public List<CompetitionCategory> getAll() {
        return getAllCompetitionCategoriesPort.getAll();
    }
}
