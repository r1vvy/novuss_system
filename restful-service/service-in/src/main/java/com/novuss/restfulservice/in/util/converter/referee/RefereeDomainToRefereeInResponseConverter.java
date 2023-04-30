package com.novuss.restfulservice.in.util.converter.referee;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.util.converter.refereeCategory.RefereeCategoryDomainToRefereeCategoryDtoConverter;
import com.novuss.restfulservice.in.dto.response.RefereeResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RefereeDomainToRefereeInResponseConverter {

    public static RefereeResponse convert(Referee referee) {
        return RefereeResponse.builder()
            .id(referee.id().toString())
            .city(referee.city())
            .createdAt(referee.createdAt())
            .updatedAt(referee.updatedAt())
            .commissionNumber(referee.commissionNumber())
            .category(Optional.ofNullable(referee.category())
                .map(RefereeCategoryDomainToRefereeCategoryDtoConverter::convert)
                .orElse(null)
            )
            .person(referee.person())
            .build();
    }
}
