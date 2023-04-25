package com.novuss.restfulservice.core.port.in.sportsClass;

import com.novuss.restfulservice.domain.SportsClass;

import java.util.List;

public interface GetAllSportsClassesUseCase {
    List<SportsClass> getAll();
}
