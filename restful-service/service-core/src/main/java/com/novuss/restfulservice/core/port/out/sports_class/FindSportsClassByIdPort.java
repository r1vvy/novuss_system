package com.novuss.restfulservice.core.port.out.sports_class;

import com.novuss.restfulservice.domain.SportsClass;

public interface FindSportsClassByIdPort {
    SportsClass getById(String id);
}
