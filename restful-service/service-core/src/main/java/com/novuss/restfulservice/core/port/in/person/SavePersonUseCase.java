package com.novuss.restfulservice.core.port.in.person;

import com.novuss.restfulservice.domain.Person;

public interface SavePersonUseCase {
    Person save(Person person);
}
