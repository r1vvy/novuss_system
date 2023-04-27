package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.player.FindPlayerByIdPort;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindPlayerByIdAdapter implements FindPlayerByIdPort {
    private final PlayerJpaRepository playerJpaRepository;
    @Override
    public Player findById(String id) {
        var playerId = UUID.fromString(id);
        return playerJpaRepository.findById(playerId)
                .map(PlayerEntityToDomainConverter::convert)
                .orElseThrow(
                        () -> new EntityNotFoundException("Player with id " + id + " not found")
                );
    }
}
