package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition.UpdateCategoryForCompetitionPort;
import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.converter.CompetitionEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.CompetitionCategoryEntity;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCategoryForCompetitionAdapter implements UpdateCategoryForCompetitionPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    private final CompetitionCategoryJpaRepository competitionCategoryJpaRepository;
    @Override
    public Competition update(String competitionId, String categoryId) {
        var competitionUUID = UUID.fromString(competitionId);

        var competitionEntity = competitionJpaRepository.findById(competitionUUID)
                .orElseThrow(() -> {
                    log.warn("No Competition Entity found with id={}", competitionId);
                    throw new EntityNotFoundException("No Competition Entity found with id=" + competitionId);
                });
        CompetitionCategoryEntity competitionCategoryEntity = null;
        if(categoryId != null) {
            var competitionCategoryUUID = UUID.fromString(categoryId);
            competitionCategoryEntity = competitionCategoryJpaRepository.findById(competitionCategoryUUID)
                    .orElseThrow(() -> {
                        log.warn("No CompetitionCategory Entity found with id={}", categoryId);
                        throw new EntityNotFoundException("No CompetitionCategory Entity found with id=" + categoryId);
                    });
        }

        competitionEntity.setCompetitionCategoryEntity(competitionCategoryEntity);
        var savedEntity = competitionJpaRepository.save(competitionEntity);
        log.info("Competition entity updated successfully: {}", savedEntity);

        return CompetitionEntityToDomainConverter.convert(savedEntity);
    }
}
