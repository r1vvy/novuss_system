package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityToDomainConverter {
    public static Person convert(PersonEntity entity) {
        return Person.builder()
                .id(entity.getId())
                .birthDay(entity.getBirthDay())
                .email(entity.getEmail())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .phoneNumber(entity.getPhoneNumber())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
