package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.out.person.SavePersonPort;
import com.novuss.restfulservice.repository.converter.PersonDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.PersonEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SavePersonAdapter implements SavePersonPort {
    private final PersonJpaRepository personJpaRepository;

    @Override
    public Person save(Person person) {
        personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                person.firstName(),
                person.lastName(),
                person.phoneNumber()
        ).ifPresent(personEntity -> {
            log.error("Person with name {} {} and phone number {} already exists",
                    person.firstName(),
                    person.lastName(),
                    person.phoneNumber()
            );
            throw new EntityExistsException("Person with name " + person.firstName() +
                    " " + person.lastName() +
                    " and phone number " + person.phoneNumber() +
                    " already exists");
        });

        var personEntity = PersonDomainToEntityConverter.convert(person);
        var savedPersonEntity = personJpaRepository.save(personEntity);
        log.info("Person with id {} saved", personEntity.getId());

        return PersonEntityToDomainConverter.convert(savedPersonEntity);
    }
}
