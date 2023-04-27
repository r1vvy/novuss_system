package com.novuss.restfulservice.repository.adapter.competitionCategory;

import com.novuss.restfulservice.core.port.out.competitionCategory.GetAllCompetitionCategoriesPort;
import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.repository.converter.CompetitionCategoryEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCompetitionCategoriesAdapter implements GetAllCompetitionCategoriesPort {
    private final CompetitionCategoryJpaRepository competitionCategoryJpaRepository;
    @Override
    public List<CompetitionCategory> getAll() {
        return competitionCategoryJpaRepository.findAll()
                .stream()
                .map(CompetitionCategoryEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
