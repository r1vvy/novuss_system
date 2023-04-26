package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.referee.DeleteRefereeByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteRefereeByIdAdapter implements DeleteRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final PlayerJpaRepository playerJpaRepository;
    private final LocationJpaRepository locationJpaRepository;

    @Override
    public void deleteById(String id) {
        var refereeId = UUID.fromString(id);

        var refereeEntity = refereeJpaRepository.findById(refereeId)
                .orElseThrow(() -> {
                    log.error("Referee with id {} not found", id);
                    throw new EntityNotFoundException("Referee with id " + id + " not found");
                });
        var personEntity = refereeEntity.getPersonEntity();
        var personEntityId = personEntity.getId();
        var playerEntity = playerJpaRepository.findByPersonId(personEntityId);
        var locationsWithPersonEntity = locationJpaRepository.countLocationEntitiesByPersonEntityId(personEntityId);

        if(playerEntity.isEmpty() && locationsWithPersonEntity == 0) {
            personJpaRepository.deleteById(personEntityId);
            log.info("Person with id {} deleted", personEntityId);
        }

        refereeJpaRepository.deleteById(refereeId);
        log.info("Referee with id {} deleted", id);
    }
}
