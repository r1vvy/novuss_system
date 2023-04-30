package com.novuss.restfulservice.core.port.in.person;

import com.novuss.restfulservice.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllPeopleUseCase {
    Page<Person> getAllByPage(Pageable pageable);
}
