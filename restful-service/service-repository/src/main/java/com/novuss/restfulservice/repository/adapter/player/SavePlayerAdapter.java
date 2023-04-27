package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.out.SavePlayerPort;
import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.repository.converter.*;
import com.novuss.restfulservice.repository.entity.ClubEntity;
import com.novuss.restfulservice.repository.entity.LicenceEntity;
import com.novuss.restfulservice.repository.entity.SportsClassEntity;
import com.novuss.restfulservice.repository.repository.jpa.*;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SavePlayerAdapter implements SavePlayerPort {
    private final PlayerJpaRepository playerJpaRepository;
    private final PersonJpaRepository personJpaRepository;

    @Override
    public Player save(Player player) {
        var person = player.person();

        var personEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                person.firstName(),
                person.lastName(),
                person.phoneNumber())
                .orElseThrow(
                        () -> {
                            log.warn("Person does not exist");
                            throw new EntityExistsException("Person does not exist");
                        }
        );
        playerJpaRepository.findByPersonEntityFirstnameAndLastnameAndPhonenumber(
                person.firstName(),
                person.lastName(),
                person.phoneNumber())
                .ifPresent(playerEntity -> {
                    log.warn("Player already exists");
                    throw new EntityExistsException("Player already exists");
                });

        var playerEntity = PlayerDomainToEntityConverter.convert(player);
        playerEntity.setPersonEntity(personEntity);

        var savedPlayerEntity = playerJpaRepository.save(playerEntity);
        log.info("Player saved: {}", savedPlayerEntity);

        return PlayerEntityToDomainConverter.convert(savedPlayerEntity);
    }
}
