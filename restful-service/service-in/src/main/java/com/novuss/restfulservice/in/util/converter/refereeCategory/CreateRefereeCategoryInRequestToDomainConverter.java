package com.novuss.restfulservice.in.util.converter.refereeCategory;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.in.dto.request.CreateRefereeCategoryInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateRefereeCategoryInRequestToDomainConverter {
    public static RefereeCategory convert (CreateRefereeCategoryInRequest request) {
        return RefereeCategory.builder()
                .title(request.title())
                .build();
    }
}
