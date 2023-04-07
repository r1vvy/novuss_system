package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnamePort;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.restfulservice.domain.Person;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindPersonByFirstnameAndLastnameAdapter implements FindPersonByFirstnameAndLastnamePort {
    private final PersonJpaRepository personJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public Optional<Person> findByFirstnameAndLastname(String name, String lastname) {
        return personJpaRepository.findByFirstNameAndLastName(name, lastname)
                .map(mapStructMapper::personEntityToDomain);
    }
}
