package com.novuss.restfulservice.repository.adapter.competition_referee;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition_referee.SaveCompetitionRefereePort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.repository.CompetitionRefereeCompositeKey;
import com.novuss.restfulservice.repository.converter.CompetitionRefereeDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionRefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionRefereeJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionRefereeAdapter implements SaveCompetitionRefereePort {
    private final CompetitionRefereeJpaRepository competitionRefereeJpaRepository;
    private final RefereeJpaRepository refereeJpaRepository;
    private final CompetitionJpaRepository competitionJpaRepository;
    @Override
    public CompetitionReferee save(String competitionId, String refereeId, CompetitionReferee competitionReferee) {
        var competitionUUID = UUID.fromString(competitionId);
        var refereeUUID = UUID.fromString(refereeId);

        var refereeEntity = refereeJpaRepository.findById(refereeUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Referee with id {} not found", refereeUUID);
                            return new EntityNotFoundException("Referee not found");
                        }
                );
        var competitionEntity = competitionJpaRepository.findById(competitionUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Competition with id {} not found", competitionUUID);
                            return new EntityNotFoundException("Competition not found");
                        }
                );

        var key = CompetitionRefereeCompositeKey.builder()
                .competitionEntityId(competitionUUID)
                .refereeEntityId(refereeUUID)
                .build();
        competitionRefereeJpaRepository.findById(key)
                .ifPresent(
                        competitionRefereeEntity -> {
                            log.warn("CompetitionReferee with id {} already exists", key);
                            throw new EntityExistsException("CompetitionReferee already exists");
                        }
                );

        var competitionRefereeEntity = CompetitionRefereeDomainToEntityConverter.convert(competitionReferee);
        var savedCompetitionRefereeEntity = competitionRefereeJpaRepository.save(competitionRefereeEntity);

        return CompetitionRefereeEntityToDomainConverter.convert(savedCompetitionRefereeEntity);
    }
}
