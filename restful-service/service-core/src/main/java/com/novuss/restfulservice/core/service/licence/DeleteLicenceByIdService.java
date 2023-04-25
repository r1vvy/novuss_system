package com.novuss.restfulservice.core.service.licence;

import com.novuss.restfulservice.core.port.in.licence.DeleteLicenceByIdUseCase;
import com.novuss.restfulservice.core.port.out.licence.DeleteLicenceByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteLicenceByIdService implements DeleteLicenceByIdUseCase {
    private final DeleteLicenceByIdPort deleteLicenceByIdPort;
    @Override
    public void deleteLicenceById(String id) {
        deleteLicenceByIdPort.deleteLicenceById(id);
    }
}
