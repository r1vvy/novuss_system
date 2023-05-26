package com.novuss.restfulservice.repository.adapter.competition_referee;

import com.novuss.restfulservice.core.port.out.competition_referee.FindAllCompetitionRefereesByCompetitionIdPort;
import com.novuss.restfulservice.domain.CompetitionReferee;
import com.novuss.restfulservice.repository.converter.CompetitionRefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionRefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllCompetitionRefereesByCompetitionIdAdapter implements FindAllCompetitionRefereesByCompetitionIdPort {
    private final CompetitionRefereeJpaRepository competitionRefereeJpaRepository;

    @Override
    public List<CompetitionReferee> findAllByCompetitionId(String competitionId) {
        var competitionUUID = UUID.fromString(competitionId);
        return competitionRefereeJpaRepository.findAllByCompetitionEntityId(competitionUUID)
                .stream()
                .map(CompetitionRefereeEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
