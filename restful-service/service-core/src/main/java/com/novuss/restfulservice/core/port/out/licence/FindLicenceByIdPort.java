package com.novuss.restfulservice.core.port.out.licence;

import com.novuss.restfulservice.domain.Licence;

import java.util.Optional;

public interface FindLicenceByIdPort {
    Optional<Licence> getById(String id);
}
