package com.novuss.restfulservice.core.port.in.referee_category;

import com.novuss.restfulservice.domain.RefereeCategory;

public interface GetRefereeCategoryByIdUseCase {
    RefereeCategory getById(String id);
}
