package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.dto.response.RefereeInResponse;
import org.springframework.stereotype.Component;

@Component
public class RefereeDomainToRefereeInResponseConverter {

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
