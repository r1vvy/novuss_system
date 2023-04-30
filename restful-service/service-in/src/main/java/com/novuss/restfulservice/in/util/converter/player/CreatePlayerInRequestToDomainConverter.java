package com.novuss.restfulservice.in.util.converter.player;

import com.novuss.restfulservice.in.dto.request.CreatePlayerInRequest;
import com.novuss.restfulservice.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class CreatePlayerInRequestToDomainConverter {
    public static Player convert(CreatePlayerInRequest createPlayerInRequest) {

        return Player.builder()
            .image(createPlayerInRequest.image())
            .rating(createPlayerInRequest.rating())
            .gender(createPlayerInRequest.gender())
            .person(createPlayerInRequest.person())
            .build();
    }
}
