package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.dto.request.UpdateRefereeInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateRefereeInRequestToDomainConverter {
        public static Referee convert(UpdateRefereeInRequest updateRefereeInRequest) {
            return Referee.builder()
                    .id(updateRefereeInRequest.id())
                    .city(updateRefereeInRequest.city())
                    .commissionNumber(updateRefereeInRequest.commissionNumber())
                    .dateIssued(updateRefereeInRequest.dateIssued())
                    .refereeCategory(updateRefereeInRequest.refereeCategory())
                    .person(updateRefereeInRequest.person())
                    .build();
        }
}
