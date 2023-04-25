package com.novuss.restfulservice.core.port.in.licence;

import com.novuss.restfulservice.domain.Licence;

public interface GetLicenceByIdUseCase {
    Licence getById(String id);
}
