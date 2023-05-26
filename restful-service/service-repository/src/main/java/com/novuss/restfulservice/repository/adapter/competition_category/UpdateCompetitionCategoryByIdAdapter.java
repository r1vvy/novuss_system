package com.novuss.restfulservice.repository.adapter.competition_category;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition_category.UpdateCompetitionCategoryByIdPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.repository.converter.CompetitionCategoryDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionCategoryEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionCategoryByIdAdapter implements UpdateCompetitionCategoryByIdPort {
    private final CompetitionCategoryJpaRepository competitionCategoryJpaRepository;
    @Override
    public CompetitionCategory updateById(CompetitionCategory competitionCategory, String id) {
        var categoryId = UUID.fromString(id);

        var existingCompetitionCategoryEntity = competitionCategoryJpaRepository.findById(categoryId)
                .orElseThrow(
                        () -> {
                            log.warn("Competition category with id {} not found", id);
                            return new EntityNotFoundException("Competition category with id " + id + " not found");
                        }
                );
        var updatedCompetitionCategoryEntity = CompetitionCategoryDomainToEntityConverter.convert(competitionCategory);
        updatedCompetitionCategoryEntity.setId(categoryId);
        updatedCompetitionCategoryEntity.setCreatedAt(existingCompetitionCategoryEntity.getCreatedAt());

        var updatedCompetitionCategoryEntityFromRepo = competitionCategoryJpaRepository.save(updatedCompetitionCategoryEntity);
        log.info("Competition category with id {} updated", id);

        return CompetitionCategoryEntityToDomainConverter.convert(updatedCompetitionCategoryEntityFromRepo);
    }
}
