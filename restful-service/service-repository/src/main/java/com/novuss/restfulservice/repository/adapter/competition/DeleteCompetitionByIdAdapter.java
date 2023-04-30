package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition.DeleteCompetitionByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteCompetitionByIdAdapter implements DeleteCompetitionByIdPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    @Override
    public void deleteById(String id) {
        var competitionUUID = UUID.fromString(id);
        competitionJpaRepository.findById(competitionUUID)
                .orElseThrow(() -> {
                    log.warn("No Competition found with id={}", id);
                    return new EntityNotFoundException("No Competition found with id=" + id);
                });

        competitionJpaRepository.deleteById(competitionUUID);
    }
}
