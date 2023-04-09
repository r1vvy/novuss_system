package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.referee.FindRefereeByIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindRefereeByIdAdapter implements FindRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;

    @Override
    public Optional<Referee> findById(String id) {
        var refereeEntity = refereeJpaRepository.findById(UUID.fromString(id));
        log.info("Referee with id {} found", id);

        return refereeEntity.map(RefereeEntityToDomainConverter::convert);
    }
}
