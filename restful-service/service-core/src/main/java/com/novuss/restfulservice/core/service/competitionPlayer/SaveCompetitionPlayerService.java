package com.novuss.restfulservice.core.service.competitionPlayer;

import com.novuss.restfulservice.core.port.in.competitionPlayer.SaveCompetitionPlayerUseCase;
import com.novuss.restfulservice.core.port.out.competitionPlayer.SaveCompetitionPlayerPort;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerByIdPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveCompetitionPlayerService implements SaveCompetitionPlayerUseCase {
    private final SaveCompetitionPlayerPort saveCompetitionPlayerPort;
    private final UpdatePlayerByIdPort updatePlayerByIdPort;
    @Override
    public CompetitionPlayer save(String competitionId, CompetitionPlayer competitionPlayer) {
        var ratingChange = competitionPlayer.ratingChange();
        if (ratingChange != null || ratingChange != 0) {
            var updatedPlayer = updatePlayerByIdPort.updatePlayerRating(ratingChange, competitionPlayer.playerId().toString());
            competitionPlayer.toBuilder()
                    .ratingAfter(updatedPlayer.rating())
                    .build();
        }

        return saveCompetitionPlayerPort.save(competitionPlayer, competitionId);
    }
}
