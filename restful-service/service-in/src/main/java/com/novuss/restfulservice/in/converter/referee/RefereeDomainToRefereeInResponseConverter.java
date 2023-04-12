package com.novuss.restfulservice.in.converter.referee;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.converter.refereeCategory.RefereeCategoryDomainToRefereeCategoryDtoConverter;
import com.novuss.restfulservice.in.dto.response.RefereeResponse;
import org.springframework.stereotype.Component;

@Component
public class RefereeDomainToRefereeInResponseConverter {

    public static RefereeResponse convert(Referee referee) {
        return RefereeResponse.builder()
            .id(referee.id().toString())
            .city(referee.city())
            .createdAt(referee.createdAt())
            .updatedAt(referee.updatedAt())
            .commissionNumber(referee.commissionNumber())
            .category(RefereeCategoryDomainToRefereeCategoryDtoConverter.convert(referee.category()))
            .person(referee.person())
            .build();
    }
}
