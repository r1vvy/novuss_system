package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.repository.entity.PlayerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerEntityToDomainConverter {

    public static Player convert(PlayerEntity entity) {
        var builder = Player.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .gender(entity.getGender())
                .rating(entity.getRating())
                .updatedAt(entity.getUpdatedAt());

        if (entity.getClubEntity() != null) {
            var club = ClubEntityToDomainConverter.convert(entity.getClubEntity());
            builder.club(club);
        }
        if (entity.getLicenceEntity() != null) {
            var licence = LicenceEntityToDomainConverter.convert(entity.getLicenceEntity());
            builder.licence(licence);
        }
        if(entity.getPersonEntity() != null) {
            var person = PersonEntityToDomainConverter.convert(entity.getPersonEntity());
            builder.person(person);
        }
        if (entity.getSportsClassEntity() != null) {
            var sportsClass = SportsClassEntityToDomainConverter.convert(entity.getSportsClassEntity());
            builder.sportsClass(sportsClass);
        }

        return builder.build();
    }
}
