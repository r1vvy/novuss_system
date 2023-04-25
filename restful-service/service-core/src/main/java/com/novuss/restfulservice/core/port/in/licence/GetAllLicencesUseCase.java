package com.novuss.restfulservice.core.port.in.licence;

import com.novuss.restfulservice.domain.Licence;

import java.util.List;

public interface GetAllLicencesUseCase {
    List<Licence> getALl();
}
