package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeByIdPort;
import com.novuss.restfulservice.domain.Referee;
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

import java.time.Instant;
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
        log.info("Updating referee with id {}", id);

        var existingReferee = findRefereeFromRepoById(UUID.fromString(id));
        var existingPerson = existingReferee.getPersonEntity();
        var existingRefereeCategory = existingReferee.getCategory();

        var updatedRefereeCategoryEntity = changeRefereeCategory(existingReferee, existingRefereeCategory, referee);
        var updatedPersonEntity = PersonDomainToEntityConverter.convert(referee.person());
        updatedPersonEntity.setId(existingPerson.getId());
        updatedPersonEntity.setCreatedAt(existingPerson.getCreatedAt());

        personJpaRepository.save(updatedPersonEntity);
        refereeCategoryJpaRepository.save(updatedRefereeCategoryEntity);

        var updatedRefereeEntity = RefereeDomainToEntityConverter.convert(referee);
        updatedRefereeEntity.setId(existingReferee.getId());
        updatedRefereeEntity.setCreatedAt(existingReferee.getCreatedAt());

        updatedRefereeEntity.setPersonEntity(updatedPersonEntity);
        updatedRefereeEntity.setCategory(updatedRefereeCategoryEntity);

        var newRefereeEntity = refereeJpaRepository.saveAndFlush(updatedRefereeEntity);
        log.info("Referee with id {} updated", id);

        return RefereeEntityToDomainConverter.convert(newRefereeEntity);
    }

    private RefereeEntity findRefereeFromRepoById(UUID id) {
        return refereeJpaRepository.findById(id).orElseThrow(() -> {
            log.error("Referee with id {} not found", id);
            throw new EntityNotFoundException("Referee with id " + id + " not found");
        });
    }
    private RefereeCategoryEntity changeRefereeCategory(RefereeEntity existingReferee, RefereeCategoryEntity existingRefereeCategory, Referee updatedReferee) {
        var updatedCategoryForReferee = refereeCategoryJpaRepository.findByTitle(updatedReferee.category().title());

        if(!updatedCategoryForReferee.isPresent()) {
            log.error("Referee category with title {} not found", updatedReferee.category().title());
            throw new EntityNotFoundException("Referee category with title " + updatedReferee.category().title() + " not found");
        }

        var updatedCategory = updatedCategoryForReferee.get();
        existingRefereeCategory.getReferees().remove(existingReferee);
        updatedCategory.addReferee(existingReferee);
        updatedCategory.setUpdatedAt(Instant.now());
        log.info("Changed referee category to {}", updatedReferee.category().title());

        return updatedCategory;
    }
}
