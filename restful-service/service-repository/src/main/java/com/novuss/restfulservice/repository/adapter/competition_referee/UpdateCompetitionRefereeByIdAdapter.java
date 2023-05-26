package com.novuss.restfulservice.repository.adapter.competition_referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition_referee.UpdateCompetitionRefereeByIdPort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.repository.CompetitionRefereeCompositeKey;
import com.novuss.restfulservice.repository.converter.CompetitionRefereeDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.CompetitionRefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionRefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionRefereeByIdAdapter implements UpdateCompetitionRefereeByIdPort {
    private final CompetitionRefereeJpaRepository competitionRefereeJpaRepository;
    @Override
    public CompetitionReferee update(String competitionId, String refereeId, CompetitionReferee competitionReferee) {
        var competitionUUID = UUID.fromString(competitionId);
        var refereeUUID = UUID.fromString(refereeId);

        var key = CompetitionRefereeCompositeKey.builder()
                .competitionEntityId(competitionUUID)
                .refereeEntityId(refereeUUID)
                .build();

        var oldEntity = competitionRefereeJpaRepository.findById(key)
                .orElseThrow(
                        () -> {
                            log.warn("CompetitionReferee with id {} not found", key);
                            return new EntityNotFoundException("CompetitionReferee not found");
                        }
                );
        var updatedEntity = CompetitionRefereeDomainToEntityConverter.convert(competitionReferee);
        updatedEntity.setId(oldEntity.getId());
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        updatedEntity = competitionRefereeJpaRepository.save(updatedEntity);

        return CompetitionRefereeEntityToDomainConverter.convert(updatedEntity);
    }
}
