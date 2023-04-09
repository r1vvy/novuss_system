package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeByIdPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.converter.*;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateRefereeByIdAdapter implements UpdateRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;
    @Override
    public Referee updateById(String id, Referee referee) {
        log.info("Updating referee with id {} ", id);
        var person = referee.person();
        var refereeCategory = referee.category();

        doesRefereeCategoryWithIdExist(refereeCategory.id());
        doesPersonWithIdExist(person.id());
        doesRefereeWithIdExist(UUID.fromString(id));

        var personEntity = personJpaRepository.save(PersonDomainToEntityConverter.convert(person));
        var refereeCategoryEntity = refereeCategoryJpaRepository.save(
                RefereeCategoryDomainToEntityConverter.convert(refereeCategory)
        );

        var refereeEntity = RefereeDomainToEntityConverter.convert(referee);
        refereeEntity.setId(UUID.fromString(id));
        refereeEntity.setPersonEntity(personEntity);
        refereeEntity.setCategory(refereeCategoryEntity);

        var newRefereeEntity = refereeJpaRepository.save(refereeEntity);
        log.info("Referee with id {} updated", id);

        return RefereeEntityToDomainConverter.convert(newRefereeEntity);
    }

    private void doesRefereeCategoryWithIdExist(UUID id) {
        refereeCategoryJpaRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Referee category with id {} not found", id);
                    return new EntityNotFoundException("Referee category with id " + id + " not found");
                }
        );
    }

    private void doesPersonWithIdExist(UUID id) {
        personJpaRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Person with id {} not found", id);
                    return new EntityNotFoundException("Person with id " + id + " not found");
                }
        );
    }

    private void doesRefereeWithIdExist(UUID id) {
        refereeJpaRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Referee with id {} not found", id);
                    return new EntityNotFoundException("Referee with id " + id + " not found");
                }
        );
    }
}
