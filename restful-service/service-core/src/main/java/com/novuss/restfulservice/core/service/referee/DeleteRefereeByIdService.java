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

    public void deleteById(String id) {
        log.info("Trying to delete referee with id = {}", id);

        deleteRefereeByIdPort.deleteById(id);
    }
}
