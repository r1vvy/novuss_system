package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnameAndPhonenumberPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.repository.converter.PersonEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindPersonByFirstnameAndLastnameAndPhonenumberAdapter implements FindPersonByFirstnameAndLastnameAndPhonenumberPort {
    private final PersonJpaRepository personJpaRepository;

    @Override
    public Optional<Person> findByFirstnameAndLastnameAndPhonenumber(String firstname, String lastname, String phonenumber) {
        var personEntity = personJpaRepository.findByFirstNameAndLastNameAndPhoneNumber(
                firstname,
                lastname,
                phonenumber
        );

        return personEntity.map(PersonEntityToDomainConverter::convert);
    }
}
