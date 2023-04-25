package com.novuss.restfulservice.core.port.in;

import com.novuss.restfulservice.domain.Licence;

public interface GetLicenceByIdUseCase {
    Licence getById(String id);
}
