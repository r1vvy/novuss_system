package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeByIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateRefereeByIdAdapter implements UpdateRefereeByIdPort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final MapStructMapper mapStructMapper;
    @Override
    public Referee updateById(String id, Referee referee) {
        var refereeEntity = mapStructMapper.refereeDomainToEntity(referee);
        refereeEntity.setId(UUID.fromString(id));

        if(!refereeJpaRepository.existsById(UUID.fromString(id))) {
            log.error("Referee with id {} does not exist", id);
            throw new EntityNotFoundException("Referee with id " + id + " does not exist");
        }

        return mapStructMapper.refereeEntityToDomain(refereeJpaRepository.save(refereeEntity));
    }
}
