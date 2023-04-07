package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.person.GetPersonByIdUseCase;
import com.novuss.restfulservice.core.port.out.person.FindPersonByIdPort;
import com.novuss.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPersonByIdService implements GetPersonByIdUseCase {
    private final FindPersonByIdPort findPersonByIdPort;

    @Override
    public Person getById(String id) {
        log.info("Searching for person with id = {}", id);
        return findPersonByIdPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person with id = " + id + " not found"));
    }
}
