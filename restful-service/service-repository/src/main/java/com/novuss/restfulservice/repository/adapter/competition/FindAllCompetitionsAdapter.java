package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.port.out.competition.FindAllCompetitionsPort;
import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.converter.CompetitionEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllCompetitionsAdapter implements FindAllCompetitionsPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    @Override
    public List<Competition> findAll() {

        return competitionJpaRepository.findAll()
                .stream()
                .map(CompetitionEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
