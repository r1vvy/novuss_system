package com.novuss.restfulservice.core.port.in.person;

import com.novuss.restfulservice.domain.Person;

public interface UpdatePersonByIdUseCase {
    Person updateById(String id, Person person);
}
