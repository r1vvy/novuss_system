package com.novuss.restfulservice.in.util.converter.referee;

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
            .person(createRefereeInRequest.person())
            .build();
    }
}
