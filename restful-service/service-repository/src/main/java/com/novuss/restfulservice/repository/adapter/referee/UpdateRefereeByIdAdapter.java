package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeByIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.RefereeDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateRefereeByIdAdapter implements UpdateRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    @Override
    public Referee updateById(String id, Referee referee) {
        var existingReferee = refereeJpaRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Referee with id " + id + " does not exist"));
        var existingPerson = personJpaRepository.findById(UUID.fromString(referee.person().id()))
                .orElseThrow(() -> new EntityNotFoundException("Person with id " + referee.person().id() + " does not exist"));
        var refereeEntity = RefereeDomainToEntityConverter.convert(referee);
        refereeEntity.setId(UUID.fromString(id));

        refereeEntity.setCreatedAt(refereeJpaRepository.findById(UUID.fromString(id))
                .get()
                .getCreatedAt()
        );

        return RefereeEntityToDomainConverter.convert(refereeJpaRepository.save(refereeEntity));
    }
}
