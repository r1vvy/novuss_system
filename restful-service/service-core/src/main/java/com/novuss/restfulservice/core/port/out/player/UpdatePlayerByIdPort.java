package com.novuss.restfulservice.core.port.out.player;

import com.novuss.restfulservice.domain.Player;

public interface UpdatePlayerByIdPort {
    Player updatePlayerById(Player player, String id);
    Player updatePlayerRating(Integer ratingChange, String id);
}
