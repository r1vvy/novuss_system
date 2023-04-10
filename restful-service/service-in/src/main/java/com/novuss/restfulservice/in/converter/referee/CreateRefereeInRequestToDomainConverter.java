package com.novuss.restfulservice.in.converter.referee;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.dto.request.CreateRefereeInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateRefereeInRequestToDomainConverter {
    public static Referee convert(CreateRefereeInRequest createRefereeInRequest) {
        return Referee.builder()
            .city(createRefereeInRequest.city())
            .commissionNumber(createRefereeInRequest.commissionNumber())
            .commissionNumber(createRefereeInRequest.commissionNumber())
            .category(createRefereeInRequest.refereeCategory())
            .person(createRefereeInRequest.person())
            .build();
    }
}
