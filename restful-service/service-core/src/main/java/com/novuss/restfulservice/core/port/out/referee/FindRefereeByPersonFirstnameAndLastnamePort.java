package com.novuss.restfulservice.core.port.out.referee;

import com.novuss.restfulservice.domain.Referee;

import java.util.Optional;

public interface FindRefereeByPersonFirstnameAndLastnamePort {
    Optional<Referee> find(String firstname, String lastname);
}
