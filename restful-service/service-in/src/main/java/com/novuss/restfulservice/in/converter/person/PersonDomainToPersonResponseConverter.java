package com.novuss.restfulservice.in.converter.person;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.in.dto.response.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonDomainToPersonResponseConverter {
    public static PersonResponse convert (Person person) {
        return PersonResponse.builder()
            .id(person.id().toString())
            .firstName(person.firstName())
            .lastName(person.lastName())
            .email(person.email())
            .phoneNumber(person.phoneNumber())
            .birthDay(person.birthDay())
            .createdAt(person.createdAt())
            .updatedAt(person.updatedAt())
            .build();
    }
}
