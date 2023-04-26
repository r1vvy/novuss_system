package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.port.out.player.DeletePlayerByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeletePlayerByIdAdapter implements DeletePlayerByIdPort {
    private final PlayerJpaRepository playerJpaRepository;
    @Override
    public void deleteById(String id) {
        var uuid = UUID.fromString(id);

        playerJpaRepository.findById(uuid).ifPresentOrElse(
                playerEntity -> {
                    log.info("Player with id {} found", id);
                    playerJpaRepository.deleteById(uuid);
                },
                () -> {
                    log.info("Player with id {} not found", id);
                    throw new RuntimeException("Player with id " + id + " not found");
                }
        );
    }
}
