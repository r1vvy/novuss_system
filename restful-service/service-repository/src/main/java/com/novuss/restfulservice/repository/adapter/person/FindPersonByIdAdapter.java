package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.FindPersonByIdPort;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindPersonByIdAdapter implements FindPersonByIdPort {
    private final PersonJpaRepository personJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public Optional<Person> findById(String id) {
        return personJpaRepository.findById(UUID.fromString(id))
                .map(mapStructMapper::personEntityToDomain);
    }
}
