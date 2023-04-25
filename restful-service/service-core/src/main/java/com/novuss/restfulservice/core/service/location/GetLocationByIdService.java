package com.novuss.restfulservice.core.service.location;

import com.novuss.restfulservice.core.port.in.location.GetLocationByIdUseCase;
import com.novuss.restfulservice.core.port.out.location.findLocationByIdPort;
import com.novuss.restfulservice.domain.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetLocationByIdService implements GetLocationByIdUseCase {
    private final findLocationByIdPort findLocationByIdPort;
    @Override
    public Location getLocationById(String id) {

        return findLocationByIdPort.getLocationById(id);
    }
}
