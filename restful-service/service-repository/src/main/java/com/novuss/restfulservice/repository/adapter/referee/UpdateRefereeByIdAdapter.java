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

        var updatedPersonEntity = PersonDomainToEntityConverter.convert(referee.person());
        var updatedRefereeCategoryEntity = RefereeCategoryDomainToEntityConverter.convert(referee.category());

        updatedPersonEntity.setId(existingPerson.getId());
        updatedRefereeCategoryEntity.setId(existingRefereeCategory.getId());

        updatedPersonEntity.setCreatedAt(existingPerson.getCreatedAt());
        updatedRefereeCategoryEntity.setCreatedAt(existingRefereeCategory.getCreatedAt());

        var updatedRefereeEntity = existingReferee.toBuilder()
                .personEntity(updatedPersonEntity)
                .category(updatedRefereeCategoryEntity)
                .build();

        var newRefereeEntity = refereeJpaRepository.save(updatedRefereeEntity);
        log.info("Referee with id {} updated", id);

        return RefereeEntityToDomainConverter.convert(newRefereeEntity);
    }

    private RefereeEntity findRefereeFromRepoById(UUID id) {
        return refereeJpaRepository.findById(id).orElseThrow(() -> {
            log.error("Referee with id {} not found", id);
            return new EntityNotFoundException("Referee with id " + id + " not found");
        });
    }
}
