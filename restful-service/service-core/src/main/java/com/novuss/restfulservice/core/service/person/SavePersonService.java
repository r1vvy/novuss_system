package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.in.person.SavePersonUseCase;
import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnameAndPhonenumberPort;
import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnamePort;
import com.novuss.restfulservice.core.port.out.person.SavePersonPort;
import com.novuss.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavePersonService implements SavePersonUseCase {
    private final SavePersonPort savePersonPort;
    private final FindPersonByFirstnameAndLastnameAndPhonenumberPort findPersonByFirstnameAndLastnameAndPhonenumberPort;

    @Override
    public Person save(Person person) {
        findPersonByFirstnameAndLastnameAndPhonenumberPort.findByFirstnameAndLastnameAndPhonenumber(
                person.firstName(),
                person.lastName(),
                person.phoneNumber()
        ).ifPresent(p -> {
            log.warn("Person with firstname = {} and lastname = {} and phonenumber = {} already exists",
                    person.firstName(),
                    person.lastName(),
                    person.phoneNumber());
            throw new EntityExistsException("Person with firstname = " + person.firstName() +
                    " and lastname = " + person.lastName() +
                    " and phonenumber = " + person.phoneNumber()
                    + " already exists");
        });

        return savePersonPort.save(person);
    }
}
