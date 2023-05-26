package com.novuss.restfulservice.core.service.competition_category;

import com.novuss.restfulservice.core.port.in.competition_category.DeleteCompetitionCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_category.DeleteCompetitionCategoryByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteCompetitionCategoryByIdService implements DeleteCompetitionCategoryByIdUseCase {
    private final DeleteCompetitionCategoryByIdPort deleteCompetitionCategoryByIdPort;
    @Override
    public void deleteById(String id) {
        deleteCompetitionCategoryByIdPort.deleteById(id);
    }
}
