package com.novuss.restfulservice.core.service.competitionCategory;

import com.novuss.restfulservice.core.port.in.competitionCategory.GetAllCompetitionCategoriesUseCase;
import com.novuss.restfulservice.core.port.out.competitionCategory.GetAllCompetitionCategoriesPort;
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
