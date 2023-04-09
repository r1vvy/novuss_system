package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnamePort;
import com.novuss.restfulservice.repository.converter.PersonEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindPersonByFirstnameAndLastnameAdapter implements FindPersonByFirstnameAndLastnamePort {
    private final PersonJpaRepository personJpaRepository;

    @Override
    public Optional<Person> findByFirstnameAndLastname(String name, String lastname) {
        var personEntity = personJpaRepository.findByFirstNameAndLastName(name, lastname);
        log.info("Person with name {} and lastname {} found", name, lastname);

        return personEntity.map(PersonEntityToDomainConverter::convert);
    }
}
