package com.novuss.restfulservice.core.service.competition_player;

import com.novuss.restfulservice.core.port.in.competition_player.DeleteCompetitionPlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.competition_player.DeleteCompetitionPlayerByIdPort;
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
