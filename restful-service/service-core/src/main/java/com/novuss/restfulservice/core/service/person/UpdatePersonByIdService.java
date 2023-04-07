package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.port.in.person.UpdatePersonByIdUseCase;
import com.novuss.restfulservice.core.port.out.person.UpdatePersonByIdPort;
import com.novuss.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePersonByIdService implements UpdatePersonByIdUseCase {
    private final UpdatePersonByIdPort updatePersonByIdPort;

    @Override
    public Person updateById(String id, Person person) {

        return updatePersonByIdPort.updateById(id, person);
    }
}