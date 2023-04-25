package com.novuss.restfulservice.core.port.out.sportsClass;

import com.novuss.restfulservice.domain.SportsClass;

public interface UpdateSportsClassByIdPort {
    SportsClass updateById(SportsClass sportsClass, String id);
}
