package com.novuss.restfulservice.core.service.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.person.GetPersonByIdUseCase;
import com.novuss.restfulservice.core.port.in.referee.GetRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.out.person.FindPersonByIdPort;
import com.novuss.restfulservice.core.port.out.referee.FindRefereeByIdPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetRefereeByIdService implements GetRefereeByIdUseCase {
    private final FindRefereeByIdPort findRefereeByIdPort;

    @Override
    public Referee getById(String id) {
        log.info("Searching for referee with id = {}", id);
        return findRefereeByIdPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Referee with id = " + id + " not found"));
    }
}
