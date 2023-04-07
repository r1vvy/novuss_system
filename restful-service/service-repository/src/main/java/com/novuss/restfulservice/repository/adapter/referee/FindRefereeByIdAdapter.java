package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.person.FindPersonByIdPort;
import com.novuss.restfulservice.core.port.out.referee.FindRefereeByIdPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
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
    private final MapStructMapper mapStructMapper;

    @Override
    public Optional<Referee> findById(String id) {
        return refereeJpaRepository.findById(UUID.fromString(id))
                .map(mapStructMapper::refereeEntityToDomain);
    }
}
