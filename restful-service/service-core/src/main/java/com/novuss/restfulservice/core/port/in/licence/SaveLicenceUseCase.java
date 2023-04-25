package com.novuss.restfulservice.core.port.in.licence;

import com.novuss.restfulservice.domain.Licence;

public interface SaveLicenceUseCase {
    Licence save(Licence licence);
}
