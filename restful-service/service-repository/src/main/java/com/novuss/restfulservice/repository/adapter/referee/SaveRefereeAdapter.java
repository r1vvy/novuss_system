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
        log.info("SaveRefereeAdapter.save: referee = {}", referee);
        var personEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                referee.person().firstName(),
                referee.person().lastName(),
                referee.person().phoneNumber()
        ).orElseThrow(() -> {
            log.warn("Person with firstName {} and lastName {} and phoneNumber {} not found",
                    referee.person().firstName(),
                    referee.person().lastName(),
                    referee.person().phoneNumber());
            throw new EntityNotFoundException("Person with firstName " + referee.person().firstName() +
                    " and lastName " + referee.person().lastName() +
                    " and phoneNumber " + referee.person().phoneNumber() + " not found");
        });
        refereeJpaRepository.findByPersonId(personEntity.getId()).ifPresent(
                refereeEntity -> {
                    log.warn("Referee with personId {} already exists", personEntity.getId());
                    throw new EntityExistsException("Referee with personId " + personEntity.getId() + " already exists");
                }
        );
        RefereeCategoryEntity refereeCategoryEntity = null;
        if(referee.category() != null) {
            refereeCategoryEntity = refereeCategoryJpaRepository.findByTitle(referee.category().title())
                    .orElseThrow(() -> {
                        log.warn("RefereeCategory with name {} not found", referee.category().title());
                        throw new EntityNotFoundException("RefereeCategory with name " + referee.category().title() + " not found");
                    });

        }

        var refereeEntity = RefereeDomainToEntityConverter.convert(referee);
        refereeEntity.setPersonEntity(personEntity);
        refereeEntity.setCategoryEntity(refereeCategoryEntity);

        var savedRefereeEntity = refereeJpaRepository.saveAndFlush(refereeEntity);
        log.info("Referee saved successfully {}", savedRefereeEntity);

        return RefereeEntityToDomainConverter.convert(savedRefereeEntity);
    }
}
