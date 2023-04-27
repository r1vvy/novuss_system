package com.novuss.restfulservice.core.service.referee;

import com.novuss.restfulservice.core.port.in.referee.UpdateRefereeCategoryForRefereeUseCase;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeCategoryForRefereePort;
import com.novuss.restfulservice.domain.Referee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateRefereeCategoryForRefereeService implements UpdateRefereeCategoryForRefereeUseCase {
    private final UpdateRefereeCategoryForRefereePort updateRefereeCategoryForRefereePort;
    @Override
    public Referee updateRefereeCategoryForReferee(String refereeId, String refereeCategoryId) {
        log.info("Updating referee category for referee with id = {}", refereeId);

        return updateRefereeCategoryForRefereePort.updateRefereeCategoryForReferee(refereeId, refereeCategoryId);
    }
}
