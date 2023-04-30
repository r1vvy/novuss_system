package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition.FindCompetitionByIdPort;
import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.converter.CompetitionEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionByIdAdapter implements FindCompetitionByIdPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    @Override
    public Competition findById(String id) {
        var competitionUUID = UUID.fromString(id);

        return competitionJpaRepository.findById(competitionUUID)
                .map(CompetitionEntityToDomainConverter::convert)
                .orElseThrow(() -> {
                    log.warn("No Competition Entity found with id={}", id);
                    return new EntityNotFoundException("No Competition Entity found with id=" + id);
                });
    }
}
