package com.novuss.restfulservice.repository.adapter.competitionReferee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competitionReferee.DeleteCompetitionRefereeByIdPort;
import com.novuss.restfulservice.repository.CompetitionRefereeCompositeKey;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionRefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteCompetitionRefereeByIdAdapter implements DeleteCompetitionRefereeByIdPort {
    private final CompetitionRefereeJpaRepository competitionRefereeJpaRepository;
    @Override
    public void delete(String competitionId, String refereeId) {
        var competitionUUID = UUID.fromString(competitionId);
        var refereeUUID = UUID.fromString(refereeId);
        var key = CompetitionRefereeCompositeKey.builder()
                .competitionEntityId(competitionUUID)
                .refereeEntityId(refereeUUID)
                .build();

        competitionRefereeJpaRepository.findById(key)
                .ifPresentOrElse(competitionRefereeJpaRepository::delete,
                        () -> {
                            log.warn("No CompetitionReferee Entity found with id={}", key);
                            throw new EntityNotFoundException("No CompetitionReferee Entity found with id=" + key);
                        });
    }
}
