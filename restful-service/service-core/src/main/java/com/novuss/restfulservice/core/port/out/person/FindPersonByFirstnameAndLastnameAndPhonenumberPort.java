package com.novuss.restfulservice.core.port.out.person;

import com.novuss.restfulservice.domain.Person;

import java.util.Optional;

public interface FindPersonByFirstnameAndLastnameAndPhonenumberPort {
    Optional<Person> findByFirstnameAndLastnameAndPhonenumber(String firstname, String lastname, String phonenumber);
}
