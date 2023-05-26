package com.novuss.restfulservice.in.util.converter.referee_category;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.in.dto.request.UpdateRefereeCategoryInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateRefereeCategoryInRequestToDomainConverter {
    public static RefereeCategory convert(UpdateRefereeCategoryInRequest updateRefereeCategoryInRequest) {
        return RefereeCategory.builder()
            .title(updateRefereeCategoryInRequest.title())
            .build();
    }
}
