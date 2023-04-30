package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.port.in.person.GetAllPeopleUseCase;
import com.novuss.restfulservice.core.port.out.person.GetAllPeoplePort;
import com.novuss.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllPeopleService implements GetAllPeopleUseCase {
    private final GetAllPeoplePort getAllPeoplePort;

    @Override
    public Page<Person> getAllByPage(Pageable pageable) {
        return getAllPeoplePort.getAllPeopleByPage(pageable);
    }
}
