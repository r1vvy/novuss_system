package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.person.UpdatePersonByIdPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdatePersonByIdAdapter implements UpdatePersonByIdPort {
    private final PersonJpaRepository personJpaRepository;
    private final MapStructMapper mapStructMapper;
    @Override
    public Person updateById(String id, Person person) {
        var personEntity = mapStructMapper.personDomainToEntity(person);
        personEntity.setId(UUID.fromString(id));

        if(!personJpaRepository.existsById(UUID.fromString(id))){
            log.error("Person with id {} does not exist", id);
            throw new EntityNotFoundException("Person with id " + id + " does not exist");
        }

        return mapStructMapper.personEntityToDomain(personJpaRepository.save(personEntity));
    }
}
