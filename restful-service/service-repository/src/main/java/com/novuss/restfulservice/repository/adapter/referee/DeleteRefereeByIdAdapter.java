package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.person.DeletePersonByIdPort;
import com.novuss.restfulservice.core.port.out.referee.DeleteRefereeByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteRefereeByIdAdapter implements DeleteRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;

    @Override
    public void deleteById(String id) {
        refereeJpaRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Referee with id {} not found", id);
                    throw new EntityNotFoundException("Referee with id " + id + " not found");
                });

        refereeJpaRepository.deleteById(UUID.fromString(id));
        log.info("Referee with id {} deleted", id);
    }
}
