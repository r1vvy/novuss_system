package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.in.person.SavePersonUseCase;
import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnamePort;
import com.novuss.restfulservice.core.port.out.person.SavePersonPort;
import com.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavePersonService implements SavePersonUseCase {
    private final FindPersonByFirstnameAndLastnamePort findPersonByFirstnameAndLastnamePort;
    private final SavePersonPort savePersonPort;

    @Override
    public Person save(Person person) {
        var firstName = person.firstName();
        var lastName = person.lastName();

        findPersonByFirstnameAndLastnamePort.findByFirstnameAndLastname(firstName, lastName)
                .ifPresent(p ->
                {
                    log.error("Person with name {} and lastname {} already exists", firstName, lastName);
                    throw new EntityExistsException("Person with name " + firstName + " and lastname " + lastName + " already exists");
                });

        return savePersonPort.save(person);
    }
}
