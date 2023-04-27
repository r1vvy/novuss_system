package com.novuss.restfulservice.core.service.location;

import com.novuss.restfulservice.core.port.in.location.UpdateLocationContactPersonByIdUseCase;
import com.novuss.restfulservice.core.port.out.location.UpdateLocationContactPersonByIdPort;
import com.novuss.restfulservice.domain.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateLocationContactPersonByIdService implements UpdateLocationContactPersonByIdUseCase {
    private final UpdateLocationContactPersonByIdPort updateLocationContactPersonByIdPort;
    @Override
    public Location updateLocationContactPersonById(String locationId, String contactPersonId) {
        return updateLocationContactPersonByIdPort.update(locationId, contactPersonId);
    }
}
