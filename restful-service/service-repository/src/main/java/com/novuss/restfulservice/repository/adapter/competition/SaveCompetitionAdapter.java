package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.out.competition.SaveCompetitionPort;
import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.converter.CompetitionDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionAdapter implements SaveCompetitionPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    private final CompetitionCategoryJpaRepository competitionCategoryJpaRepository;
    @Override
    public Competition save(Competition competition) {
        var title = competition.title();
        competitionJpaRepository.findByTitle(title)
                .ifPresent(
                        competitionEntity -> {
                            log.warn("Competition with title={} already exists", title);
                            throw new EntityExistsException("Competition with title=" + title + " already exists");
                        }
                );
        var location = locationJpaRepository.findById(competition.location().id())
            .orElseThrow(
                () -> {
                    log.warn("Location with id={} does not exist", competition.location().id());
                    return new EntityExistsException("Location with id=" + competition.location().id() + " does not exist");
                }
        );
        var category = competitionCategoryJpaRepository.findById(competition.category().id()).orElseThrow(
                () -> {
                    log.warn("Competition category with id={} does not exist", competition.category().id());
                    return new EntityExistsException("Competition category with id=" + competition.category().id() + " does not exist");
                }
        );


        var competitionEntity = CompetitionDomainToEntityConverter.convert(competition);
        competitionEntity.setLocationEntity(location);
        competitionEntity.setCompetitionCategoryEntity(category);

        var savedEntity = competitionJpaRepository.save(competitionEntity);
        log.info("Competition entity saved successfully: {}", savedEntity);

        return CompetitionEntityToDomainConverter.convert(savedEntity);
    }
}
