package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.location.DeleteLocationByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DeleteLocationByIdAdapter implements DeleteLocationByIdPort {
    private final LocationJpaRepository locationJpaRepository;
    private final RefereeJpaRepository refereeJpaRepository;
    private final PlayerJpaRepository playerJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    @Override
    public void deleteLocationById(String id) {
        UUID uuid = UUID.fromString(id);
        var locationEntity = locationJpaRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Location not found with id = " + id)
        );
        var personEntity = Optional.ofNullable(locationEntity.getPersonEntity());
        personEntity.ifPresent(
                person -> {
                    var refereeEntity = refereeJpaRepository.findByPersonId(person.getId());
                    var playerEntity = playerJpaRepository.findByPersonId(person.getId());

                    if(refereeEntity.isEmpty() || playerEntity.isEmpty()) {
                        personJpaRepository.deleteById(person.getId());
                        log.info("Person with id {} deleted", person.getId());
                    }
                }
        );

        locationJpaRepository.deleteById(uuid);
        log.info("Location with id {} deleted", id);
    }
}
