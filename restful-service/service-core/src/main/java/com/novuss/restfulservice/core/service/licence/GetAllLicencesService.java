package com.novuss.restfulservice.core.service.licence;

import com.novuss.restfulservice.core.port.in.licence.GetAllLicencesUseCase;
import com.novuss.restfulservice.core.port.out.licence.GetAllLicencesPort;
import com.novuss.restfulservice.domain.Licence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllLicencesService implements GetAllLicencesUseCase {
    private final GetAllLicencesPort getAllLicencesPort;
    @Override
    public List<Licence> getALl() {
         return getAllLicencesPort.getAllLicences();
    }
}
