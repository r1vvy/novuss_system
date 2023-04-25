package com.novuss.restfulservice.core.service.licence;

import com.novuss.restfulservice.core.port.in.licence.SaveLicenceUseCase;
import com.novuss.restfulservice.core.port.out.licence.SaveLicencePort;
import com.novuss.restfulservice.domain.Licence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveLicenceService implements SaveLicenceUseCase {
    private final SaveLicencePort saveLicencePort;
    @Override
    public Licence save(Licence licence) {
        return saveLicencePort.save(licence);
    }
}
