package com.novuss.restfulservice.core.service.competitionPlayer;

import com.novuss.restfulservice.core.port.in.competitionPlayer.DeleteCompetitionPlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionPlayer.DeleteCompetitionPlayerByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCompetitionPlayerByIdService implements DeleteCompetitionPlayerByIdUseCase {
    private final DeleteCompetitionPlayerByIdPort deleteCompetitionPlayerByIdPort;
    @Override
    public void deleteById(String competitionId, String playerId) {
        deleteCompetitionPlayerByIdPort.deleteById(competitionId, playerId);
    }
}
