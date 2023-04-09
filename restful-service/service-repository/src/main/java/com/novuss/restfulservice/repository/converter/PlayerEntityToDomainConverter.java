package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.entity.PlayerEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerEntityToDomainConverter {

    public static Player convert(PlayerEntity entity) {

        return Player.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .gender(entity.getGender())
                .image(entity.getImage())
                .person(PersonEntityToDomainConverter.convert(entity.getPersonEntity()))
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
