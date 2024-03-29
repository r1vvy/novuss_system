package com.novuss.restfulservice.in.util.converter.person;

import com.novuss.restfulservice.in.dto.request.CreatePersonInRequest;
import com.novuss.restfulservice.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonInRequestToDomainConverter {

    public static Person convert(CreatePersonInRequest createPersonInRequest) {
        return Person.builder()
            .firstName(createPersonInRequest.firstName())
            .lastName(createPersonInRequest.lastName())
            .birthDay(createPersonInRequest.birthDay())
            .email(createPersonInRequest.email())
            .phoneNumber(createPersonInRequest.phoneNumber())
            .build();
    }
}
