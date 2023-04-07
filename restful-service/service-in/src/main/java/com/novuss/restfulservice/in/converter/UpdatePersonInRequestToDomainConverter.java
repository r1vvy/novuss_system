package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.in.dto.request.UpdatePersonInRequest;
import com.restfulservice.domain.Person;
import lombok.Builder;

@Builder
public class UpdatePersonInRequestToDomainConverter {

    public static Person convert(UpdatePersonInRequest updatePersonInRequest) {
        return Person.builder()
                .firstName(updatePersonInRequest.firstName())
                .lastName(updatePersonInRequest.lastName())
                .birthDay(updatePersonInRequest.birthDay())
                .phoneNumber(updatePersonInRequest.phoneNumber())
                .email(updatePersonInRequest.email())
                .build();
    }
}
