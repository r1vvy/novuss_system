package com.novuss.restfulservice.core.service.referee;

import com.novuss.restfulservice.core.port.in.referee.UpdateRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.out.person.UpdatePersonByIdPort;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeByIdPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateRefereeByIdService implements UpdateRefereeByIdUseCase {
    private final UpdateRefereeByIdPort updateRefereeByIdPort;

    @Override
    public Referee updateById(String id, Referee referee) {

        return updateRefereeByIdPort.updateById(id, referee);
    }
}