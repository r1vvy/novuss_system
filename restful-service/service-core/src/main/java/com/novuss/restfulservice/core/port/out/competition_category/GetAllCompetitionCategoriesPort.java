package com.novuss.restfulservice.core.port.out.competition_category;

import com.novuss.restfulservice.domain.CompetitionCategory;

import java.util.List;

public interface GetAllCompetitionCategoriesPort {
    List<CompetitionCategory> getAll();
}
