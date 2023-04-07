package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.in.referee.SaveRefereeUseCase;
import com.novuss.restfulservice.core.port.out.person.SavePersonPort;
import com.novuss.restfulservice.core.port.out.referee.SaveRefereePort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SaveRefereeAdapter implements SaveRefereePort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public Referee save(Referee referee) {
        var person = referee.person();
        var personEntity = personJpaRepository.findByFirstNameAndLastName(person.firstName(), person.lastName())
                .orElseGet(() -> {
                    var newPerson = mapStructMapper.personDomainToEntity(person);
                    return personJpaRepository.save(newPerson);
                });
        var refereeEntity = mapStructMapper.refereeDomainToEntity(referee);
        refereeEntity.setPersonEntity(personEntity);

        return mapStructMapper.refereeEntityToDomain(refereeJpaRepository.save(refereeEntity));
    }
}
