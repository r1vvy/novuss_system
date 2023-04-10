package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.referee.FindRefereeByPersonFirstnameAndLastnamePort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindRefereeByPersonFirstnameAndLastnameAdapter implements FindRefereeByPersonFirstnameAndLastnamePort {
    private final RefereeJpaRepository refereeJpaRepository;

    @Override
    public Optional<Referee> find(String name, String lastname) {
        log.info("Trying to find referee by name {} and lastname {}", name, lastname);
        var refereeEntity = refereeJpaRepository.findByPersonFirstNameAndLastName(name, lastname);

        return refereeEntity.map(RefereeEntityToDomainConverter::convert);
    }
}
