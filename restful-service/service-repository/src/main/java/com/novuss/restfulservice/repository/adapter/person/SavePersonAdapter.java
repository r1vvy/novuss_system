package com.novuss.restfulservice.repository.adapter.person;

import com.novuss.restfulservice.core.port.out.SavePlayerPort;
import com.novuss.restfulservice.core.port.out.person.SavePersonPort;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.restfulservice.domain.Person;
import com.restfulservice.domain.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SavePersonAdapter implements SavePersonPort {
    private final PersonJpaRepository personJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public Person save(Person person) {
        var personEntity = mapStructMapper.personDomainToEntity(person);

        return mapStructMapper.personEntityToDomain(personJpaRepository.save(personEntity));
    }
}
