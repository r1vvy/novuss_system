package com.novuss.restfulservice.core.service.competition;

import com.novuss.restfulservice.core.port.in.competition.DeleteCompetitionByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition.DeleteCompetitionByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCompetitionByIdService implements DeleteCompetitionByIdUseCase {
    private final DeleteCompetitionByIdPort deleteCompetitionByIdPort;
    @Override
    public void deleteById(String id) {
        deleteCompetitionByIdPort.deleteById(id);
    }
}
