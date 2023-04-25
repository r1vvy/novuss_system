package com.novuss.restfulservice.core.port.in.sportsClass;

import com.novuss.restfulservice.domain.SportsClass;

public interface GetSportsClassByIdUseCase {
    SportsClass getById(String id);
}
