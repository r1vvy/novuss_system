package com.novuss.restfulservice.core.port.in.sports_class;

import com.novuss.restfulservice.domain.SportsClass;

import java.util.List;

public interface GetAllSportsClassesUseCase {
    List<SportsClass> getAll();
}
