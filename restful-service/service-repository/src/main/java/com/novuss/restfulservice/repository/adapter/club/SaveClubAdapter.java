package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.club.SaveClubPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.converter.LocationDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.PersonDomainToEntityConverter;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SaveClubAdapter implements SaveClubPort {
    private final ClubJpaRepository clubJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public Club save(Club club) {
        log.info("Trying to save club = {}", club);
        var person = Optional.ofNullable(club.contactPerson());
        var location = Optional.ofNullable(club.location());

        clubJpaRepository.findClubEntityByTitle(club.title())
                .ifPresent(clubEntity -> {
                    log.warn("Club already exists with title = {}", club.title());
                    throw new EntityExistsException("Club already exists with title = " + club.title());
        });

        PersonEntity personEntity = null;
        if(person.isPresent()) {
            personEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                    person.get().firstName(),
                    person.get().lastName(),
                    person.get().phoneNumber())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Associated person with club not found")
                    );
        }
        LocationEntity locationEntity = null;
        if(location.isPresent()) {
            locationEntity = locationJpaRepository.findByTitle(location.get().title())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Associated location with club not found")
                    );
        }

        var clubEntity = ClubDomainToEntityConverter.convert(club);
        clubEntity.setContactPersonEntity(personEntity);
        clubEntity.setLocationEntity(locationEntity);

        var newClubEntity = clubJpaRepository.save(clubEntity);
        log.info("New club entity saved = {}", newClubEntity);

        return ClubEntityToDomainConverter.convert(newClubEntity);
    }
}
