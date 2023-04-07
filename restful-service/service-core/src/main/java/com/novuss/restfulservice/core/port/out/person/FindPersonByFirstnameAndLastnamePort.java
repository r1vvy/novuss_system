package com.novuss.restfulservice.core.port.out.person;

import com.novuss.restfulservice.domain.Person;

import java.util.Optional;

public interface FindPersonByFirstnameAndLastnamePort {
    Optional<Person> findByFirstnameAndLastname(String name, String lastname);
}
