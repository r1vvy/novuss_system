package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.entity.PlayerEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlayerDomainToEntityConverter {
    public static PlayerEntity convert(Player player) {
        var builder = PlayerEntity.builder()
                .id(player.id())
                .image(player.image())
                .gender(player.gender())
                .rating(player.rating())
                .createdAt(player.createdAt())
                .updatedAt(player.updatedAt());

        if (player.club() != null) {
            var clubEntity = ClubDomainToEntityConverter.convert(player.club());
            builder.clubEntity(clubEntity);
        }
        if (player.licence() != null) {
            var licenceEntity = LicenceDomainToEntityConverter.convert(player.licence());
            builder.licenceEntity(licenceEntity);
        }
        if(player.person() != null) {
            var personEntity = PersonDomainToEntityConverter.convert(player.person());
            builder.personEntity(personEntity);
        }
        if (player.sportsClass() != null) {
            var sportsClassEntity = SportsClassDomainToEntityConverter.convert(player.sportsClass());
            builder.sportsClassEntity(sportsClassEntity);
        }

        return builder.build();
    }
}
