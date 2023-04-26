package com.novuss.restfulservice.core.service.player;

import com.novuss.restfulservice.core.port.in.player.DeletePlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.player.DeletePlayerByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeletePlayerByIdService implements DeletePlayerByIdUseCase {
    private final DeletePlayerByIdPort deletePlayerByIdPort;
    @Override
    public void deleteById(String id) {
        deletePlayerByIdPort.deleteById(id);
    }
}
