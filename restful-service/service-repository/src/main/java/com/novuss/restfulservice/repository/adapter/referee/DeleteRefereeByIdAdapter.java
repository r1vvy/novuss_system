package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.person.DeletePersonByIdPort;
import com.novuss.restfulservice.core.port.out.referee.DeleteRefereeByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
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
        log.info("Deleting person with id = {}", id);
        refereeJpaRepository.deleteById(UUID.fromString(id));
    }
}
