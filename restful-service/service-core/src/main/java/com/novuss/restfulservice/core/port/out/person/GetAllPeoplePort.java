package com.novuss.restfulservice.core.port.out.person;

import com.novuss.restfulservice.domain.Person;

import java.util.List;

public interface GetAllPeoplePort {
    List<Person> getAllPeople();
}
