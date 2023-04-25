package com.novuss.restfulservice.core.port.out.licence;

import com.novuss.restfulservice.domain.Licence;

public interface SaveLicencePort {
    Licence save(Licence licence);
}
