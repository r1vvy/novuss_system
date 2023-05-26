package com.novuss.restfulservice.core.port.in.sports_class;

import com.novuss.restfulservice.domain.SportsClass;

public interface UpdateSportsClassByIdUseCase {
    SportsClass update(SportsClass sportsClass, String id);
}
