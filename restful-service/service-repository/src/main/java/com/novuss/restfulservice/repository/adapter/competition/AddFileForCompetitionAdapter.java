package com.novuss.restfulservice.repository.adapter.competition;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.competition.AddFileForCompetitionPort;
import com.novuss.restfulservice.repository.repository.jpa.CompetitionJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddFileForCompetitionAdapter implements AddFileForCompetitionPort {
    private final CompetitionJpaRepository competitionJpaRepository;
    private final FileJpaRepository fileJpaRepository;
    @Override
    public void addFileById(String competitionId, String fileId) {
        var fileUUID = UUID.fromString(fileId);
        var competitionUUID = UUID.fromString(competitionId);

        var competition = competitionJpaRepository.findById(competitionUUID).orElseThrow(
                () -> new EntityNotFoundException("Competition not found")
        );
        var file = fileJpaRepository.findById(fileUUID).orElseThrow(
                () -> new EntityNotFoundException("File not found")
        );
        log.info("Adding file {} to competition {}", file, competition);

        file.setCompetitionEntity(competition);
        fileJpaRepository.save(file);
        log.info("File {} added to competition {}", file, competition);
    }
}
