package com.novuss.restfulservice.core.service.competitionCategory;

import com.novuss.restfulservice.core.port.in.competitionCategory.DeleteCompetitionCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionCategory.DeleteCompetitionCategoryByIdPort;
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
