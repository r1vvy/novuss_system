package com.novuss.restfulservice.in.converter.player;

import com.novuss.restfulservice.in.dto.response.CreatePlayerResponse;
import com.novuss.restfulservice.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerDomainToCreatePlayerResponseConverter {
    public static CreatePlayerResponse convert (Player player) {
        return CreatePlayerResponse.builder()
            .id(player.id().toString())
            .image(player.image())
            .gender(player.gender())
            .person(player.person())
            .rating(player.rating())
            .createdAt(player.createdAt())
            .updatedAt(player.updatedAt())
            .build();
    }
}
