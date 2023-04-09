package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.in.dto.request.CreatePlayerInRequest;
import com.novuss.restfulservice.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class CreatePlayerInRequestToDomainConverter {
    public static Player convert(CreatePlayerInRequest createPlayerInRequest) {

        return Player.builder()
            .image(createPlayerInRequest.image())
            .rating(createPlayerInRequest.rating())
            .person(createPlayerInRequest.person())
            .gender(createPlayerInRequest.gender())
            .build();
    }
}
