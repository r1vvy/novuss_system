package com.novuss.restfulservice.repository.adapter.competitionCategory;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.out.competitionCategory.SaveCompetitionCategoryPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.repository.converter.CompetitionCategoryDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionCategoryEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionCategoryAdapter implements SaveCompetitionCategoryPort {
    private final CompetitionCategoryJpaRepository competitionCategoryJpaRepository;
    @Override
    public CompetitionCategory save(CompetitionCategory competitionCategory) {
        competitionCategoryJpaRepository.findByTitle(competitionCategory.title())
                .ifPresent(
                        competitionCategoryEntity -> {
                            log.warn("CompetitionCategory with title {} already exists", competitionCategory.title());
                            throw new EntityExistsException("CompetitionCategory with title " + competitionCategory.title() + " already exists");
                        }
                );

        var competitionCategoryEntity = CompetitionCategoryDomainToEntityConverter.convert(competitionCategory);
        var savedCompetitionCategoryEntity = competitionCategoryJpaRepository.save(competitionCategoryEntity);

        return CompetitionCategoryEntityToDomainConverter.convert(savedCompetitionCategoryEntity);
    }
}
