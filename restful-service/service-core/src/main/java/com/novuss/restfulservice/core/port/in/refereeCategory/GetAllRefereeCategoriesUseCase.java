package com.novuss.restfulservice.core.port.in.refereeCategory;

import com.novuss.restfulservice.domain.RefereeCategory;

import java.util.List;

public interface GetAllRefereeCategoriesUseCase {
    List<RefereeCategory> getAll();
}
