package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.GetAllPeoplePort;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllPeopleAdapter implements GetAllPeoplePort {
    private final PersonJpaRepository personJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public List<Person> getAllPeople() {
        return personJpaRepository.findAll()
                .stream()
                .map(mapStructMapper::personEntityToDomain)
                .collect(Collectors.toList());
    }
}
