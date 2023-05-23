package com.novuss.restfulservice.in.util.converter.player;

import com.novuss.restfulservice.domain.Gender;
import com.novuss.restfulservice.domain.Player;
import com.novuss.restfulservice.in.dto.request.UpdatePlayerInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdatePlayerInRequestToDomainConverter {

    public static Player convert(UpdatePlayerInRequest request) {
        return Player.builder()
                .rating(request.rating())
                .gender(Gender.valueOf(request.gender()))
                .build();
    }
}
