package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersonDomainToEntityConverter {
    public static PersonEntity convert(Person person) {
        return PersonEntity.builder()
                .id(person.id())
                .birthDay(person.birthDay())
                .email(person.email())
                .lastName(person.lastName())
                .firstName(person.firstName())
                .phoneNumber(person.phoneNumber())
                .createdAt(person.createdAt())
                .updatedAt(person.updatedAt())
                .build();
    }
}
