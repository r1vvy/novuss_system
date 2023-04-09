package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.person.UpdatePersonByIdPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.repository.converter.PersonDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.PersonEntityToDomainConverter;
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
    @Override
    public Person updateById(String id, Person person) {
        if(!personJpaRepository.existsById(UUID.fromString(id))){
            log.error("Person with id {} does not exist", id);
            throw new EntityNotFoundException("Person with id " + id + " does not exist");
        }

        var personEntity = PersonDomainToEntityConverter.convert(person);
        personEntity.setId(UUID.fromString(id));
        personEntity.setCreatedAt(personJpaRepository.findById(UUID.fromString(id))
                .get()
                .getCreatedAt()
        );

        var updatedPerson = PersonEntityToDomainConverter.convert(personJpaRepository.save(personEntity));
        log.info("Person with id {} updated", id);

        return updatedPerson;
    }
}
