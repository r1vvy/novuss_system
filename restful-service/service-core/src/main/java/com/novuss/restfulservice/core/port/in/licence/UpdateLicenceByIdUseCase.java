package com.novuss.restfulservice.core.port.in.licence;

import com.novuss.restfulservice.domain.Licence;

public interface UpdateLicenceByIdUseCase {
    Licence updateLicenceById(Licence licence, String id);
}
