package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.out.competition.SaveCompetitionPort;
import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.converter.CompetitionDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionAdapter implements SaveCompetitionPort {
    private final CompetitionJpaRepository competitionJpaRepository;
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
        var competitionEntity = CompetitionDomainToEntityConverter.convert(competition);
        var savedEntity = competitionJpaRepository.save(competitionEntity);
        log.info("Competition entity saved successfully: {}", savedEntity);

        return CompetitionEntityToDomainConverter.convert(savedEntity);
    }
}
