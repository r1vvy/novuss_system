package com.novuss.restfulservice.core.port.out.refereeCategory;

import com.novuss.restfulservice.domain.RefereeCategory;

import java.util.Optional;

public interface FindRefereeCategoryByIdPort {
        Optional<RefereeCategory> findById(String id);
}
