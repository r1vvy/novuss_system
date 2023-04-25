package com.novuss.restfulservice.core.port.out.licence;

import com.novuss.restfulservice.domain.Licence;

public interface UpdateLicenceByIdPort {
    Licence updateLicenceById(Licence licence, String id);
}
