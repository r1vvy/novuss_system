package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.dto.request.UpdateRefereeInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateRefereeInRequestToDomainConverter {
        public static Referee convert(UpdateRefereeInRequest updateRefereeInRequest) {
            return Referee.builder()
                .city(updateRefereeInRequest.city())
                .commissionNumber(updateRefereeInRequest.commissionNumber())
                .category(updateRefereeInRequest.refereeCategory())
                .person(updateRefereeInRequest.person())
                .build();
        }
}
