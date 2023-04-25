package com.novuss.restfulservice.repository.adapter.licence;

import com.novuss.restfulservice.core.port.out.licence.GetAllLicencesPort;
import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.repository.converter.LicenceEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LicenceJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllLicencesAdapter implements GetAllLicencesPort {
    private final LicenceJpaRepository licenceJpaRepository;
    @Override
    public List<Licence> getAllLicences() {
        return licenceJpaRepository.findAll()
                .stream()
                .map(LicenceEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
