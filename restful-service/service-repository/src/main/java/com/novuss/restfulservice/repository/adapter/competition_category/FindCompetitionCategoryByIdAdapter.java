package com.novuss.restfulservice.repository.adapter.competition_category;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition_category.FindCompetitionCategoryByIdPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.repository.converter.CompetitionCategoryEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionCategoryByIdAdapter implements FindCompetitionCategoryByIdPort {
    private final CompetitionCategoryJpaRepository competitionCategoryJpaRepository;
    @Override
    public CompetitionCategory findById(String id) {
        var competitionCategoryEntity = competitionCategoryJpaRepository.findById(UUID.fromString(id))
                .orElseThrow(() ->
                {
                    log.error("Competition category with id {} not found", id);
                    throw new EntityNotFoundException("Competition category with id " + id + " not found");
                });

        return CompetitionCategoryEntityToDomainConverter.convert(competitionCategoryEntity);
    }
}
