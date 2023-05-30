package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.GetCompetitionFilesByCompIdUseCase;
import com.novuss.restfulservice.core.port.out.competition.GetCompetitionFilesByCompIdPort;
import com.novuss.restfulservice.domain.FileDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCompetitionFilesByCompIdService implements GetCompetitionFilesByCompIdUseCase {
    private final GetCompetitionFilesByCompIdPort getCompetitionFilesByCompIdPort;
    @Override
    public List<FileDomain> getCompetitionFilesByCompId(String id) {
        return getCompetitionFilesByCompIdPort.getCompetitionFilesByCompId(id);
    }
}
