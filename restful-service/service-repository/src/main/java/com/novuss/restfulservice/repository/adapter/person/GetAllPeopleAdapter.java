package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.GetAllPeoplePort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.repository.converter.PersonEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllPeopleAdapter implements GetAllPeoplePort {
    private final PersonJpaRepository personJpaRepository;

    @Override
    public Page<Person> getAllPeopleByPage(Pageable pageable) {
        var page = personJpaRepository.findAll(pageable);

        return page.map(PersonEntityToDomainConverter::convert);
    }
}
