package com.novuss.restfulservice.in.converter.player;

import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.in.converter.club.ClubDomainToClubResponseConverter;
import com.novuss.restfulservice.in.converter.licence.LicenceDomainToLicenceResponseConverter;
import com.novuss.restfulservice.in.converter.person.PersonDomainToPersonResponseConverter;
import com.novuss.restfulservice.in.converter.sportsClass.SportsClassDomainToResponseConverter;
import com.novuss.restfulservice.in.dto.response.PlayerResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerDomainToPlayerResponseConverter {

    public static PlayerResponse convert(Player domain) {
        return PlayerResponse.builder()
                .id(domain.id().toString())
                .gender(domain.gender().toString())
                .image(domain.image())
                .rating(domain.rating())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .person(Optional.ofNullable(
                        domain.person())
                        .map(PersonDomainToPersonResponseConverter::convert)
                        .orElse(null)
                )
                .club(Optional.ofNullable(
                        domain.club())
                        .map(ClubDomainToClubResponseConverter::convert)
                        .orElse(null)
                )
                .licence(Optional.ofNullable(
                        domain.licence())
                        .map(LicenceDomainToLicenceResponseConverter::convert)
                        .orElse(null)
                )
                .sportsClass(Optional.ofNullable(
                        domain.sportsClass())
                        .map(SportsClassDomainToResponseConverter::convert)
                        .orElse(null)
                )
                .build();
    }
}
