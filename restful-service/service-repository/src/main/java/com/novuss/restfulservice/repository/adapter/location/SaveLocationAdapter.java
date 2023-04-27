package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.port.out.location.SaveLocationPort;
import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.converter.LocationDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.LocationEntityToDomainConverter;
import com.novuss.restfulservice.repository.converter.PersonDomainToEntityConverter;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SaveLocationAdapter implements SaveLocationPort {
    private final LocationJpaRepository locationJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    @Override
    public Location saveLocation(Location location) {
        locationJpaRepository.findByTitle(location.title())
                .ifPresent(
                        (existingLocation) -> {
                            log.warn("Location already exists with title = {}", location.title());
                            throw new EntityExistsException("Location already exists with title = " + location.title());
                        }
                );
        var contactPerson = location.contactPerson();
        var contactPersonEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                contactPerson.firstName(),
                contactPerson.lastName(),
                contactPerson.phoneNumber()
        ).orElseThrow(
                () -> new EntityExistsException("Person not found with firstName = " + contactPerson.firstName() +
                        " lastName = " + contactPerson.lastName() +
                        " phoneNumber = " + contactPerson.phoneNumber())
        );

        var locationEntity = LocationDomainToEntityConverter.convert(location);
        locationEntity.setPersonEntity(contactPersonEntity);

        var updatedEntity = locationJpaRepository.save(locationEntity);
        log.info("Location saved with id = {}", updatedEntity.getId());

        return LocationEntityToDomainConverter.convert(updatedEntity);
    }
}
