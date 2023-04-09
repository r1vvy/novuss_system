package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.in.dto.response.PersonInResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonDomainToPersonInResponseConverter {
    public static PersonInResponse convert (Person person) {
        return PersonInResponse.builder()
            .id(person.id())
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
