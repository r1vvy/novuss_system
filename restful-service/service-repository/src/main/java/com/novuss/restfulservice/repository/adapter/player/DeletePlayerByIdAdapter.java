package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.port.out.player.DeletePlayerByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.*;
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
    @Override
    public void deleteById(String id) {
        var playerId = UUID.fromString(id);
        playerJpaRepository.findById(playerId)
                .orElseThrow(
                        () -> new RuntimeException("Player not found with id = " + id)
                );

        playerJpaRepository.deleteById(playerId);
        log.info("Player with id {} deleted", id);
    }
}
