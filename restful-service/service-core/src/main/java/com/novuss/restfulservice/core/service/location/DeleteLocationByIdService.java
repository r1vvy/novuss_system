package com.novuss.restfulservice.core.service.location;

import com.novuss.restfulservice.core.port.in.location.DeleteLocationByIdUseCase;
import com.novuss.restfulservice.core.port.out.location.DeleteLocationByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteLocationByIdService implements DeleteLocationByIdUseCase {
    private final DeleteLocationByIdPort deleteLocationByIdPort;
    @Override
    public void deleteLocationById(String id) {
        deleteLocationByIdPort.deleteLocationById(id);
    }
}
