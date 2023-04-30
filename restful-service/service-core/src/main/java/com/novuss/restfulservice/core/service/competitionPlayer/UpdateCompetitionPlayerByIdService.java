package com.novuss.restfulservice.core.service.competitionPlayer;

import com.novuss.restfulservice.core.port.in.competitionPlayer.UpdateCompetitionPlayerByIdUseCase;
import com.novuss.restfulservice.core.port.out.competitionPlayer.UpdateCompetitionPlayerByIdPort;
import com.novuss.restfulservice.core.port.out.player.UpdatePlayerByIdPort;
import com.novuss.restfulservice.domain.CompetitionPlayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCompetitionPlayerByIdService implements UpdateCompetitionPlayerByIdUseCase {
    private final UpdateCompetitionPlayerByIdPort updateCompetitionPlayerByIdPort;
    private final UpdatePlayerByIdPort updatePlayerByIdPort;
    @Override
    public CompetitionPlayer updateById(CompetitionPlayer competitionPlayer, String playerId, String competitionId) {
        var ratingChange = competitionPlayer.ratingChange();
        if (ratingChange != null || ratingChange != 0) {
            var updatedPlayer = updatePlayerByIdPort.updatePlayerRating(ratingChange, competitionPlayer.playerId().toString());
            competitionPlayer.toBuilder()
                    .ratingAfter(updatedPlayer.rating())
                    .build();
            log.debug("Competition player rating after competition: {}", competitionPlayer.ratingAfter());
        }

        var updatedCompetitionPlayer = updateCompetitionPlayerByIdPort.updateById(
                playerId,
                competitionId,
                competitionPlayer
        );


        return updatedCompetitionPlayer;
    }
}
