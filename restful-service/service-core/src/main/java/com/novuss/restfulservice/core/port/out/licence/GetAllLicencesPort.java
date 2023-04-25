package com.novuss.restfulservice.core.port.out.licence;

import com.novuss.restfulservice.domain.Licence;

import java.util.List;

public interface GetAllLicencesPort {
    List<Licence> getAllLicences();
}
