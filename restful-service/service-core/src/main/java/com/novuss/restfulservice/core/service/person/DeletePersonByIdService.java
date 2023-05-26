package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.port.in.person.DeletePersonByIdUseCase;
import com.novuss.restfulservice.core.port.out.person.DeletePersonByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeletePersonByIdService implements DeletePersonByIdUseCase {
    private final DeletePersonByIdPort deletePersonByIdPort;

    @Override
    public void deleteById(String id) {
        log.info("Deleting person with id = {}", id);

        deletePersonByIdPort.deleteById(id);
    }
}
