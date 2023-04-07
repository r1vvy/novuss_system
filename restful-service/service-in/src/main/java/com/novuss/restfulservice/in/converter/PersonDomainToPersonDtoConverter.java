package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.dto.response.PersonResponse;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.in.dto.response.RefereeInResponse;
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

    @Component
    public static class RefereeDomainToRefereeInResponseConverter {
        public static RefereeInResponse convert(Referee referee) {
            return RefereeInResponse.builder()
                    .id(referee.id())
                    .city(referee.city())
                    .commissionNumber(referee.commissionNumber())
                    .dateIssued(referee.dateIssued())
                    .refereeCategory(referee.refereeCategory())
                    .person(referee.person())
                    .build();
        }
    }
}
