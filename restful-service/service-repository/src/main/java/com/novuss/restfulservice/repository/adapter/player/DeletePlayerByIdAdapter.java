package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.port.out.player.DeletePlayerByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DeletePlayerByIdAdapter implements DeletePlayerByIdPort {
    private final PlayerJpaRepository playerJpaRepository;
    private final RefereeJpaRepository refereeJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public void deleteById(String id) {
        var playerId = UUID.fromString(id);

        var playerEntity = playerJpaRepository.findById(playerId)
                .orElseThrow(
                        () -> new RuntimeException("Player not found with id = " + id)
                );
        var personEntity = playerEntity.getPersonEntity();
        var personEntityId = personEntity.getId();
        var refereeEntity = refereeJpaRepository.findByPersonId(personEntityId);
        var locationsWithPersonEntity = locationJpaRepository.countLocationEntitiesByPersonEntityId(personEntityId);

        if (refereeEntity.isEmpty() && locationsWithPersonEntity == 0) {
            personJpaRepository.deleteById(personEntityId);
            log.info("Person with id {} deleted", personEntityId);
        }

        playerJpaRepository.deleteById(playerId);
        log.info("Player with id {} deleted", id);
    }
}
