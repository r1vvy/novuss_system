package com.novuss.restfulservice.core.port.out.person;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;

public interface UpdatePersonByIdPort {
    Person updateById(String id, Person person);
}
