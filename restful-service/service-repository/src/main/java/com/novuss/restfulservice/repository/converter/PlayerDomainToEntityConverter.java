package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.entity.PlayerEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlayerDomainToEntityConverter {
    public static PlayerEntity convert(Player player) {
        return PlayerEntity.builder()
                .id(player.id())
                .personEntity(PersonDomainToEntityConverter.convert(player.person()))
                .image(player.image())
                .gender(player.gender())
                .rating(player.rating())
                .createdAt(player.createdAt())
                .updatedAt(player.updatedAt())
                .build();
    }
}
