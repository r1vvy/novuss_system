package com.novuss.restfulservice.core.port.out.person;

import com.novuss.restfulservice.domain.Person;

public interface SavePersonPort {
        Person save(Person person);
}
