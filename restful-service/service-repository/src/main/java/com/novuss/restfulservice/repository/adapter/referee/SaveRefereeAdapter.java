package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.SaveRefereePort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.converter.PersonDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.RefereeCategoryDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.RefereeDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
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
        log.info("Trying to save referee with firstname = {} and lastname = {}",
                referee.person().firstName(),
                referee.person().lastName()
        );

        var refereeCategory = findRefereeCategoryByTitle(referee.category().title());
        var personEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                referee.person().firstName(),
                referee.person().lastName(),
                referee.person().phoneNumber()
        ).orElseGet(() -> personJpaRepository.save(PersonDomainToEntityConverter.convert(referee.person())));

        refereeJpaRepository.findByPersonId(personEntity.getId())
                .ifPresent(refereeEntity -> {
                    log.error("Referee already exists {}", refereeEntity);
                    throw new EntityExistsException("Referee already exists");
        });

        var refereeEntity = RefereeDomainToEntityConverter.convert(referee);
        refereeEntity.setCategory(refereeCategory);
        refereeEntity.setPersonEntity(personEntity);

        var savedRefereeEntity = refereeJpaRepository.save(refereeEntity);
        log.info("Referee saved successfully {}", savedRefereeEntity);

        return RefereeEntityToDomainConverter.convert(savedRefereeEntity);
    }

    private RefereeCategoryEntity findRefereeCategoryByTitle(String title) {
        return refereeCategoryJpaRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Referee category not found"));
    }
}
