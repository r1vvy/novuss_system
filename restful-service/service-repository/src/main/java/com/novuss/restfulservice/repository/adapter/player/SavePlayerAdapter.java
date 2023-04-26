package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.out.SavePlayerPort;
import com.novuss.restfulservice.repository.converter.PlayerDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SavePlayerAdapter implements SavePlayerPort {
    private final PlayerJpaRepository playerJpaRepository;

    @Override
    public Player save(Player player) {
        var person = player.person();
        playerJpaRepository.findByPersonEntityFirstnameAndLastnameAndPhonenumber(
                person.firstName(),
                person.lastName(),
                person.phoneNumber())
                .ifPresent(playerEntity -> {
                    throw new EntityExistsException("Player already exists");
                });

        var playerEntity = PlayerDomainToEntityConverter.convert(player);
        var savedPlayerEntity = playerJpaRepository.save(playerEntity);

        return PlayerEntityToDomainConverter.convert(savedPlayerEntity);
    }
}
