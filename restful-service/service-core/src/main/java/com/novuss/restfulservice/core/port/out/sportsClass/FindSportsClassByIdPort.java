package com.novuss.restfulservice.core.port.out.sportsClass;

import com.novuss.restfulservice.domain.SportsClass;

public interface FindSportsClassByIdPort {
    SportsClass getById(String id);
}
