package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeByIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.*;
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
        log.info("Updating referee with id = {}", id);
        var existingReferee = findRefereeFromRepoById(UUID.fromString(id));

        var updatedRefereeEntity = RefereeDomainToEntityConverter.convert(referee);
        updatedRefereeEntity.setId(existingReferee.getId());
        updatedRefereeEntity.setCreatedAt(existingReferee.getCreatedAt());
        updatedRefereeEntity.setCategoryEntity(existingReferee.getCategoryEntity());
        updatedRefereeEntity.setPersonEntity(existingReferee.getPersonEntity());

        var newRefereeEntity = refereeJpaRepository.saveAndFlush(updatedRefereeEntity);
        log.info("Updated referee with id = {}", id);
        log.debug("Updated referee = {}", newRefereeEntity);

        return RefereeEntityToDomainConverter.convert(newRefereeEntity);
    }

    private RefereeEntity findRefereeFromRepoById(UUID id) {
        return refereeJpaRepository.findById(id).orElseThrow(() -> {
            log.warn("Referee not found with id = {}", id);
            throw new EntityNotFoundException("Referee with id " + id + " not found");
        });
    }
}
