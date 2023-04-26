package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.player.FindPlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.player.FindPlayerByIdPort;
import com.novuss.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindPlayerByIdService implements FindPlayerByIdUseCase {
    private final FindPlayerByIdPort findPlayerByIdPort;
    @Override
    public Player getById(String id) {
        return findPlayerByIdPort.findById(id);
    }
}
