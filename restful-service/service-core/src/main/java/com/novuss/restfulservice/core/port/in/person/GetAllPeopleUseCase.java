package com.novuss.restfulservice.core.port.in.person;

import com.novuss.restfulservice.domain.Person;

import java.util.List;

public interface GetAllPeopleUseCase {
    List<Person> getAll();
}
