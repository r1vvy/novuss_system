package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.port.in.person.GetAllPeopleUseCase;
import com.novuss.restfulservice.core.port.out.person.GetAllPeoplePort;
import com.novuss.restfulservice.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllPeopleService implements GetAllPeopleUseCase {
    private final GetAllPeoplePort getAllPeoplePort;
    @Override
    public List<Person> getAll() {
        return getAllPeoplePort.getAllPeople();
    }
}
