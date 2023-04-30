package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.club.UpdateClubLocationByIdPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateClubLocationByIdAdapter implements UpdateClubLocationByIdPort {
    private final ClubJpaRepository clubJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public Club updateClubLocationById(String clubId, String locationId) {
        UUID locationUUID;
        var clubUUID = UUID.fromString(clubId);
        var clubEntity = clubJpaRepository.findById(clubUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Club with id {} not found", clubId);
                            throw new EntityNotFoundException("Club with id " + clubId + " not found");
                        }
                );
        LocationEntity locationEntity = null;
        if(locationId != null) {
            locationUUID = UUID.fromString(locationId);
            locationEntity = locationJpaRepository.findById(locationUUID)
                    .orElseThrow(
                            () -> {
                                log.warn("Location with id {} not found", locationId);
                                throw new EntityNotFoundException("Location with id " + locationId + " not found");
                            }
                    );
        }

        clubEntity.setLocationEntity(locationEntity);
        clubJpaRepository.save(clubEntity);
        log.info("Club entity updated successfully: {}", clubEntity);

        return ClubEntityToDomainConverter.convert(clubEntity);
    }
}
