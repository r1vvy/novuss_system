package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.in.dto.response.PersonResponse;
import com.restfulservice.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDomainToPersonDtoConverter {

    public static PersonResponse convert(Person person) {
        return PersonResponse.builder()
                .id(person.id())
                .firstName(person.firstName())
                .lastName(person.lastName())
                .birthDay(person.birthDay())
                .email(person.email())
                .phoneNumber(person.phoneNumber())
                .createdAt(person.createdAt())
                .updatedAt(person.updatedAt())
                .build();
    }
}
