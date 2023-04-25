package com.novuss.restfulservice.repository.adapter.sportsClass;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.sportsClass.UpdateSportsClassByIdPort;
import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.repository.converter.SportsClassDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.SportsClassEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.SportsClassJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateSportsClassByIdAdapter implements UpdateSportsClassByIdPort {
    private final SportsClassJpaRepository sportsClassJpaRepository;
    @Override
    public SportsClass updateById(SportsClass sportsClass, String id) {

        var uuid = UUID.fromString(id);
        var oldEntity = sportsClassJpaRepository.findById(uuid)
                .orElseThrow(
                        () -> new EntityNotFoundException("SportsClass not found with id = " + id)
                );
        var updatedEntity = SportsClassDomainToEntityConverter.convert(sportsClass);
        updatedEntity.setId(uuid);
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        var updatedSportsClass = sportsClassJpaRepository.save(updatedEntity);
        return SportsClassEntityToDomainConverter.convert(updatedSportsClass);
    }
}
