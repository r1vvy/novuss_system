package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersonDomainToEntityConverter {
    public static PersonEntity convert(Person person) {
        return PersonEntity.builder()
                .birthDay(person.birthDay())
                .createdAt(person.createdAt())
                .updatedAt(person.updatedAt())
                .email(person.email())
                .lastName(person.lastName())
                .firstName(person.firstName())
                .phoneNumber(person.phoneNumber())
                .build();
    }
}
