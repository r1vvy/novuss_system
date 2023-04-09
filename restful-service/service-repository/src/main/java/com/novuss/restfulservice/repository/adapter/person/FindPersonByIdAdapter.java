package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.FindPersonByIdPort;
import com.novuss.restfulservice.repository.converter.PersonEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.domain.Person;
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

    @Override
    public Optional<Person> findById(String id) {
        var personEntity = personJpaRepository.findById(UUID.fromString(id));
        log.info("Person with id {} found", id);
        return personEntity.map(PersonEntityToDomainConverter::convert);
    }
}
