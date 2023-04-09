package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.person.DeletePersonByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeletePersonByIdAdapter implements DeletePersonByIdPort {
    private final PersonJpaRepository personJpaRepository;

    @Override
    public void deleteById(String id) {
        personJpaRepository.deleteById(UUID.fromString(id));
        log.info("Person with id {} deleted", id);
    }
}
