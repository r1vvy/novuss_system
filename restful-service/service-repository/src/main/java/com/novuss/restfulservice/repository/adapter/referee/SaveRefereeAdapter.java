package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.SaveRefereePort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.PersonDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.RefereeDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SaveRefereeAdapter implements SaveRefereePort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;

    @Override
    public Referee save(Referee referee) {
        log.info("Saving referee with firstname = {} and lastname = {}",
                referee.person().firstName(),
                referee.person().lastName()
        );

        PersonEntity personEntity = findOrCreatePerson(referee);
        RefereeCategoryEntity refereeCategory = findRefereeCategory(referee);
        RefereeEntity savedRefereeEntity = newReferee(referee, personEntity, refereeCategory);

        return RefereeEntityToDomainConverter.convert(savedRefereeEntity);
    }

    private PersonEntity findOrCreatePerson(Referee referee) {
        Optional<PersonEntity> existingPerson = findExistingPerson(referee);
        if (existingPerson.isPresent()) {
            return updateExistingPerson(existingPerson.get());
        } else {
            return createNewPerson(referee);
        }
    }

    private Optional<PersonEntity> findExistingPerson(Referee referee) {
        return personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                referee.person().firstName(),
                referee.person().lastName(),
                referee.person().phoneNumber()
        );
    }

    private PersonEntity updateExistingPerson(PersonEntity personEntity) {
        log.info("Person already exists, updating person: {}", personEntity);
        validatePersonIsNotAlreadyReferee(personEntity);
        personEntity.setIsReferee(true);
        log.info("Person updated: {}", personEntity);

        return personEntity;
    }

    private PersonEntity createNewPerson(Referee referee) {
        PersonEntity personEntity = PersonDomainToEntityConverter.convert(referee.person());
        personEntity.setIsReferee(true);
        log.info("Person does not exist, creating new person: {}", personEntity);

        return personJpaRepository.save(personEntity);
    }

    private void validatePersonIsNotAlreadyReferee(PersonEntity personEntity) {
        if (personEntity.getIsReferee()) {
            log.error("Person is already a referee: {}", personEntity);
            throw new EntityExistsException("Referee already exists");
        }
    }

    private RefereeCategoryEntity findRefereeCategory(Referee referee) {
        return refereeCategoryJpaRepository.findByTitle(referee.category().title())
                .orElseThrow(() -> new EntityNotFoundException("Referee category not found"));
    }

    private RefereeEntity newReferee(Referee referee, PersonEntity savedPersonEntity, RefereeCategoryEntity refereeCategory) {
        RefereeEntity refereeEntity = RefereeDomainToEntityConverter.convert(referee);
        refereeEntity.setPersonEntity(savedPersonEntity);
        refereeEntity.setCategory(refereeCategory);
        RefereeEntity savedRefereeEntity = refereeJpaRepository.save(refereeEntity);
        log.info("Referee saved: {}", savedRefereeEntity);

        return savedRefereeEntity;
    }
}
