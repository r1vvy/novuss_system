package com.novuss.restfulservice.in.converter.referee;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.dto.RefereeInCategoryDto;
import org.springframework.stereotype.Component;

@Component
public class RefereeDomainToRefereeInCategoryDtoConverter {

    public static RefereeInCategoryDto convert (Referee referee) {
        return RefereeInCategoryDto.builder()
                .id(referee.id().toString())
                .firstName(referee.person().firstName())
                .lastName(referee.person().lastName())
                .birthDate(referee.person().birthDay())
                .city(referee.city())
                .email(referee.person().email())
                .phoneNumber(referee.person().phoneNumber())
                .commissionNumber(referee.commissionNumber())
                .build();
    }
}
