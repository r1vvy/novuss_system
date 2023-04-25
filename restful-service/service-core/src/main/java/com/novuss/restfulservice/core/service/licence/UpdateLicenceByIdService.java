package com.novuss.restfulservice.core.service.licence;

import com.novuss.restfulservice.core.port.in.licence.UpdateLicenceByIdUseCase;
import com.novuss.restfulservice.core.port.out.licence.UpdateLicenceByIdPort;
import com.novuss.restfulservice.domain.Licence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateLicenceByIdService implements UpdateLicenceByIdUseCase {
    private final UpdateLicenceByIdPort updateLicenceByIdPort;
    @Override
    public Licence updateLicenceById(Licence licence, String id) {
        return updateLicenceByIdPort.updateLicenceById(licence, id);
    }
}
