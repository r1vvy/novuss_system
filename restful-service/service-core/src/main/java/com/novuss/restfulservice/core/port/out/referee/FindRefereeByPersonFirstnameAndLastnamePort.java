package com.novuss.restfulservice.core.port.out.referee;

import com.novuss.restfulservice.domain.Referee;

public interface FindRefereeByPersonFirstnameAndLastnamePort {
    Referee find(String firstname, String lastname);
}
