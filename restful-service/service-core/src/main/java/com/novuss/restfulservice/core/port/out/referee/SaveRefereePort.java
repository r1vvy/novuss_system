package com.novuss.restfulservice.core.port.out.referee;
import com.novuss.restfulservice.domain.Referee;

import java.sql.SQLIntegrityConstraintViolationException;

public interface SaveRefereePort {
    Referee save(Referee referee);
}
