package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.location.UpdateLocationContactPersonByIdPort;
import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.converter.LocationEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateLocationContactPersonByIdAdapter implements UpdateLocationContactPersonByIdPort {
    private final LocationJpaRepository locationJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    @Override
    public Location update(String locationId, String contactPersonId) {
        var locationUUID = UUID.fromString(locationId);
        var contactPersonUUID = contactPersonId != null ? UUID.fromString(contactPersonId) : null;

        var locationEntity = locationJpaRepository.findById(locationUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Location with id {} not found", locationId);
                            return new EntityNotFoundException("Location not found with id " + locationId);
                        }
                );
        PersonEntity contactPersonEntity = null;
        if (contactPersonUUID != null) {
            contactPersonEntity = personJpaRepository.findById(contactPersonUUID)
                    .orElseThrow(
                            () -> {
                                log.warn("Person with id {} not found", contactPersonId);
                                return new EntityNotFoundException("Person not found with id " + contactPersonId);
                            }
                    );
        }
        locationEntity.setPersonEntity(contactPersonEntity);
        var updatedLocationEntity = locationJpaRepository.save(locationEntity);

        return LocationEntityToDomainConverter.convert(updatedLocationEntity);
    }
}
