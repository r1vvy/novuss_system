package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.referee.DeleteRefereeByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteRefereeByIdAdapter implements DeleteRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;

    @Override
    public void deleteById(String id) {
        var refereeId = UUID.fromString(id);
        refereeJpaRepository.findById(refereeId)
                .orElseThrow(() -> {
                    log.warn("Referee with id {} not found", id);
                    throw new EntityNotFoundException("Referee with id " + id + " not found");
                });

        refereeJpaRepository.deleteById(refereeId);
        log.info("Referee with id {} deleted", id);
    }
}
