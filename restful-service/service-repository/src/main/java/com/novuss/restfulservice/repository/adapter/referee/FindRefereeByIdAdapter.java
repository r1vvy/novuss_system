package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.FindRefereeByIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindRefereeByIdAdapter implements FindRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;

    @Override
    public Referee findById(String id) {
        var refereeEntity = refereeJpaRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Referee with id {} not found", id);
                    throw new EntityNotFoundException("Referee with id " + id + " not found");
                });

        return RefereeEntityToDomainConverter.convert(refereeEntity);
    }
}
