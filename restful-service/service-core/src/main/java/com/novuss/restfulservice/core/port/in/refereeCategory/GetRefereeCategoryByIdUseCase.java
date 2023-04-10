package com.novuss.restfulservice.core.port.in.refereeCategory;

import com.novuss.restfulservice.domain.RefereeCategory;

public interface GetRefereeCategoryByIdUseCase {
    RefereeCategory getById(String id);
}
