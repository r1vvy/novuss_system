package com.novuss.restfulservice.core.port.out.referee;
import com.novuss.restfulservice.domain.Referee;

public interface SaveRefereePort {
    Referee save(Referee referee);
}
