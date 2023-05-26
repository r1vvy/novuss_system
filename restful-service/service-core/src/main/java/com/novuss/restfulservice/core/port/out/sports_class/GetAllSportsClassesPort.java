package com.novuss.restfulservice.core.port.out.sports_class;

import com.novuss.restfulservice.domain.SportsClass;

import java.util.List;

public interface GetAllSportsClassesPort {
    List<SportsClass> getAll();
}
