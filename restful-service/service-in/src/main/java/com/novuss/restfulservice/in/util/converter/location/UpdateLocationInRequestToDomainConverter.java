package com.novuss.restfulservice.in.util.converter.location;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.in.dto.request.UpdateLocationInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateLocationInRequestToDomainConverter {
    public static Location convert(UpdateLocationInRequest request) {
        return Location.builder()
                .city(request.city())
                .address(request.address())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .build();
    }
}
