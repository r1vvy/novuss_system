package com.novuss.restfulservice.repository.adapter.competition_category;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition_category.DeleteCompetitionCategoryByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteCompetitionCategoryByIdAdapter implements DeleteCompetitionCategoryByIdPort {
    private final CompetitionCategoryJpaRepository competitionCategoryJpaRepository;
    @Override
    public void deleteById(String id) {
        var categoryId = UUID.fromString(id);
        competitionCategoryJpaRepository.findById(categoryId)
                .orElseThrow(() -> {
                    log.error("Competition category with id {} not found", id);
                    throw new EntityNotFoundException("Competition category with id " + id + " not found");
                });

        competitionCategoryJpaRepository.deleteById(categoryId);
    }
}
