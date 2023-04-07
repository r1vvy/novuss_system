package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnamePort;
import com.novuss.restfulservice.core.port.out.referee.FindRefereeByPersonFirstnameAndLastnamePort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindRefereeByPersonFirstnameAndLastnameAdapter implements FindRefereeByPersonFirstnameAndLastnamePort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public Optional<Referee> find(String name, String lastname) {
        return refereeJpaRepository.findByPersonFirstNameAndLastName(name, lastname)
                .map(mapStructMapper::refereeEntityToDomain);
    }
}
