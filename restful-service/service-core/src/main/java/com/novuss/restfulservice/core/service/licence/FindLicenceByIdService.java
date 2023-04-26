package com.novuss.restfulservice.core.service.licence;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.licence.FindLicenceByIdUseCase;
import com.novuss.restfulservice.core.port.out.licence.FindLicenceByIdPort;
import com.novuss.restfulservice.domain.Licence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindLicenceByIdService implements FindLicenceByIdUseCase {
    private final FindLicenceByIdPort findLicenceByIdPort;
    @Override
    public Licence getById(String id) {
        log.info("Searching for licence with id = {}", id);
        return findLicenceByIdPort.getById(id).orElseThrow(
                () -> new EntityNotFoundException("Licence with id = " + id + " not found")
        );
    }
}
