package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.in.dto.PersonDto;
import com.restfulservice.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoToDomainConverter {

    public static Person convert(PersonDto personDto) {
        return Person.builder()
                .firstName(personDto.firstName())
                .lastName(personDto.lastName())
                .birthDay(personDto.birthDay())
                .build();
    }
}
