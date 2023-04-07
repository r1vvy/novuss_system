package com.novuss.restfulservice.core.service.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.DeleteRefereeByIdPort;
import com.novuss.restfulservice.core.port.in.referee.DeleteRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.out.referee.FindRefereeByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteRefereeByIdService implements DeleteRefereeByIdUseCase {
    private final DeleteRefereeByIdPort deleteRefereeByIdPort;
    private final FindRefereeByIdPort findRefereeByIdPort;

    public void deleteById(String id) {
        log.info("Deleting person with id = {}", id);
        findRefereeByIdPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person with id = " + id + " not found"));

        deleteRefereeByIdPort.deleteById(id);
    }
}
