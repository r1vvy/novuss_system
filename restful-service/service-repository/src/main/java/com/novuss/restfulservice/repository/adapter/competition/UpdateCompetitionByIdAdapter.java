package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition.UpdateCompetitionByIdPort;
import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.converter.CompetitionDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionByIdAdapter implements UpdateCompetitionByIdPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    @Override
    public Competition update(String id, Competition competition) {
        var competitionUUID = UUID.fromString(id);
        var oldEntity = competitionJpaRepository.findById(competitionUUID)
                .orElseThrow(() -> {
                    log.warn("No Competition Entity found with id={}", id);
                    throw new EntityNotFoundException("No Competition Entity found with id=" + id);
                });

        var updatedEntity = CompetitionDomainToEntityConverter.convert(competition);
        updatedEntity.setId(oldEntity.getId());
        updatedEntity.setCompetitionCategoryEntity(oldEntity.getCompetitionCategoryEntity());
        updatedEntity.setLocationEntity(oldEntity.getLocationEntity());

        var savedEntity = competitionJpaRepository.save(updatedEntity);
        log.info("Competition entity updated successfully: {}", savedEntity);

        return CompetitionEntityToDomainConverter.convert(savedEntity);
    }
}
