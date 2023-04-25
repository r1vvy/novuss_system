package com.novuss.restfulservice.in.converter.player;

import com.novuss.restfulservice.domain.Gender;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.in.dto.request.UpdatePlayerInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdatePlayerInRequestToDomainConverter {

    public static Player convert(UpdatePlayerInRequest request) {
        return Player.builder()
                .image(request.image())
                .rating(request.rating())
                .gender(Gender.valueOf(request.gender()))
                .person(request.person())
                .licence(request.licence())
                .sportsClass(request.sportsClass())
                .club(request.club())
                .build();
    }
}
