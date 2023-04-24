package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.SaveRefereePort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.*;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        log.info("Trying to save referee with firstname = {} and lastname = {}",
                referee.person().firstName(),
                referee.person().lastName()
        );
        var refereeCategoryEntity = findRefereeCategoryByTitle(referee.category().title());

        var optionalPersonEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                referee.person().firstName(),
                referee.person().lastName(),
                referee.person().phoneNumber()
        );

        PersonEntity personEntity;
        if(optionalPersonEntity.isPresent()) {
            personEntity = optionalPersonEntity.get();
            var refereeEntity = refereeJpaRepository.findByPersonId(personEntity.getId());
            if(refereeEntity.isPresent()) {
                throw new EntityExistsException("Referee already exists");
            } else {
                personEntity = personJpaRepository.save(personEntity);
            }
        } else {
            personEntity = PersonDomainToEntityConverter.convert(referee.person());
            personJpaRepository.save(personEntity);
        }

        var refereeEntity = RefereeDomainToEntityConverter.convert(referee);
        refereeEntity.setPersonEntity(personEntity);
        refereeEntity.setCategoryEntity(refereeCategoryEntity);

        var savedRefereeEntity = refereeJpaRepository.saveAndFlush(refereeEntity);
        log.info("Referee saved successfully {}", savedRefereeEntity);

        return RefereeEntityToDomainConverter.convert(savedRefereeEntity);
    }

    private RefereeCategoryEntity findRefereeCategoryByTitle(String title) {
        return refereeCategoryJpaRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Referee category not found"));
    }
}
