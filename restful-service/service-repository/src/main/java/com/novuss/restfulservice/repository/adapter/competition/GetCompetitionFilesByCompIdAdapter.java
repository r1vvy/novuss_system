package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.port.out.competition.GetCompetitionFilesByCompIdPort;
import com.novuss.restfulservice.domain.FileDomain;
import com.novuss.restfulservice.repository.converter.FileEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetCompetitionFilesByCompIdAdapter implements GetCompetitionFilesByCompIdPort {
    private final FileJpaRepository fileJpaRepository;
    @Override
    public List<FileDomain> getCompetitionFilesByCompId(String id) {
        var competitionUUID = UUID.fromString(id);

        var files = fileJpaRepository.findByCompetitionEntityId(competitionUUID);

        return files.stream()
                .map(FileEntityToDomainConverter::convert)
                .toList();
    }
}
