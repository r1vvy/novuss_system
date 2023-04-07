package com.novuss.restfulservice.core.port.out.person;

import com.novuss.restfulservice.domain.Person;

import java.util.Optional;

public interface FindPersonByIdPort {
    Optional<Person> findById(String id);
}
