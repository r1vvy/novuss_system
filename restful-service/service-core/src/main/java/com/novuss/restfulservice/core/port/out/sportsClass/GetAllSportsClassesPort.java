package com.novuss.restfulservice.core.port.out.sportsClass;

import com.novuss.restfulservice.domain.SportsClass;

import java.util.List;

public interface GetAllSportsClassesPort {
    List<SportsClass> getAll();
}
