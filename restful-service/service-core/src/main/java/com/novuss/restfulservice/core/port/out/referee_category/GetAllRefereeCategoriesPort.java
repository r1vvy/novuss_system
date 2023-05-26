package com.novuss.restfulservice.core.port.out.referee_category;

import com.novuss.restfulservice.domain.RefereeCategory;

import java.util.List;

public interface GetAllRefereeCategoriesPort {
    List<RefereeCategory> getAll();
}
