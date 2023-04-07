package com.novuss.restfulservice.core.service.person;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.person.DeletePersonByIdPort;
import com.novuss.restfulservice.core.port.out.person.FindPersonByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeletePersonByIdService {
    private final DeletePersonByIdPort deletePersonByIdPort;
    private final FindPersonByIdPort findPersonByIdPort;

    public void deleteById(String id) {
        log.info("Deleting person with id = {}", id);
        findPersonByIdPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person with id = " + id + " not found"));

        deletePersonByIdPort.deleteById(id);
    }
}
