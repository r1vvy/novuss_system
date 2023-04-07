package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.in.dto.request.CreatePlayerInRequest;
import com.restfulservice.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class CreatePlayerInRequestToDomainConverter {
    public static Player convert(CreatePlayerInRequest createPlayerInRequest) {
        var person = PersonDtoToDomainConverter.convert(createPlayerInRequest.person());

        return Player.builder()
                .image(createPlayerInRequest.image())
                .rating(createPlayerInRequest.rating())
                .person(person)
                .gender(createPlayerInRequest.gender())
                .build();
    }
}
