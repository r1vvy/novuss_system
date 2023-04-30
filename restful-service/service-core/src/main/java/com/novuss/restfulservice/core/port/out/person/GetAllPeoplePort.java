package com.novuss.restfulservice.core.port.out.person;

import com.novuss.restfulservice.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllPeoplePort {
    Page<Person> getAllPeopleByPage(Pageable pageable);
}
