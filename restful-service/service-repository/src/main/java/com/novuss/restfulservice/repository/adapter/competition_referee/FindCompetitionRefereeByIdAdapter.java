package com.novuss.restfulservice.repository.adapter.competition_referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition_referee.FindCompetitionRefereeByIdPort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.repository.CompetitionRefereeCompositeKey;
import com.novuss.restfulservice.repository.converter.CompetitionRefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionRefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindCompetitionRefereeByIdAdapter implements FindCompetitionRefereeByIdPort {
    private final CompetitionRefereeJpaRepository competitionRefereeJpaRepository;
    @Override
    public CompetitionReferee findById(String competitionId, String refereeId) {
        var competitionUUID = UUID.fromString(competitionId);
        var refereeUUID = UUID.fromString(refereeId);
        var key = CompetitionRefereeCompositeKey.builder()
                .competitionEntityId(competitionUUID)
                .refereeEntityId(refereeUUID)
                .build();

        return competitionRefereeJpaRepository.findById(key)
                .map(CompetitionRefereeEntityToDomainConverter::convert)
                .orElseThrow(
                        () -> {
                            log.warn("CompetitionReferee with id {} not found", key);
                            throw new EntityNotFoundException("CompetitionReferee not found");
                        }
                );
    }
}
