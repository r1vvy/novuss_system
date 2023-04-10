package com.novuss.restfulservice.core.port.out.refereeCategory;

import com.novuss.restfulservice.domain.RefereeCategory;

public interface UpdateRefereeCategoryByIdPort {
    RefereeCategory updateById(String id, RefereeCategory category);
}
