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
        var personEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                contactPerson.firstName(),
                contactPerson.lastName(),
                contactPerson.phoneNumber()
        ).orElseGet(
                () -> {
                    var newEntity = personJpaRepository.save(PersonDomainToEntityConverter.convert(contactPerson));
                    log.info("Person saved with firstName = {}, lastName = {}, phoneNumber = {}",
                            contactPerson.firstName(),
                            contactPerson.lastName(),
                            contactPerson.phoneNumber()
                    );

                    return newEntity;
                }
        );
        log.debug("person entity= {}", personEntity);

        var locationEntity = LocationDomainToEntityConverter.convert(location);
        locationEntity.setPersonEntity(personEntity);

        log.debug("person entity= {}", location.contactPerson());

        var updatedEntity = locationJpaRepository.save(locationEntity);
        log.info("Location saved with id = {}", updatedEntity.getId());
        log.debug("Location contact person = {}", updatedEntity.getPersonEntity());

        return LocationEntityToDomainConverter.convert(updatedEntity);
    }
}
