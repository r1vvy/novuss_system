package com.novuss.restfulservice.core.port.in.refereeCategory;

import com.novuss.restfulservice.domain.RefereeCategory;

public interface UpdateRefereeCategoryByIdUseCase {
    RefereeCategory updateById(String id, RefereeCategory refereeCategory);
}
