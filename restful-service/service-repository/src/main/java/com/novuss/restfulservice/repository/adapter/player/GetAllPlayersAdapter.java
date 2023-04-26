package com.novuss.restfulservice.repository.adapter.player;

import com.novuss.restfulservice.core.port.out.player.GetAllPlayersPort;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.converter.PlayerEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PlayerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllPlayersAdapter implements GetAllPlayersPort {
    private final PlayerJpaRepository playerJpaRepository;
    @Override
    public List<Player> getAllPlayers() {
         return playerJpaRepository.findAll()
                 .stream()
                 .map(PlayerEntityToDomainConverter::convert)
                 .collect(Collectors.toList());
    }
}
