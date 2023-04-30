package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition.UpdateLocationForCompetitionPort;
import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.converter.CompetitionEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateLocationForCompetitionAdapter implements UpdateLocationForCompetitionPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public Competition update(String competitionId, String locationId) {
        var competitionUUID = UUID.fromString(competitionId);

        var competitionEntity = competitionJpaRepository.findById(competitionUUID)
                .orElseThrow(() -> {
                    log.warn("No Competition Entity found with id={}", competitionId);
                    throw new EntityNotFoundException("No Competition Entity found with id=" + competitionId);
                });

        LocationEntity locationEntity = null;
        if(locationId != null) {
            var locationUUID = UUID.fromString(locationId);
            locationEntity = locationJpaRepository.findById(locationUUID)
                    .orElseThrow(() -> {
                        log.warn("No Location Entity found with id={}", locationId);
                        throw new EntityNotFoundException("No Location Entity found with id=" + locationId);
                    });
        }
        competitionEntity.setLocationEntity(locationEntity);
        var savedEntity = competitionJpaRepository.save(competitionEntity);
        log.info("Competition entity updated successfully: {}", savedEntity);

        return CompetitionEntityToDomainConverter.convert(savedEntity);
    }
}
