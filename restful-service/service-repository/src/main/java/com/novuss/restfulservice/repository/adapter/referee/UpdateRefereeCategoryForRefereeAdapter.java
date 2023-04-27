package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeCategoryForRefereePort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateRefereeCategoryForRefereeAdapter implements UpdateRefereeCategoryForRefereePort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;
    @Override
    public Referee updateRefereeCategoryForReferee(String refereeId, String refereeCategoryId) {

        UUID refereeCategoryUUID;
        RefereeCategoryEntity refereeCategoryEntity = null;
        if(refereeCategoryId != null) {
            refereeCategoryUUID = UUID.fromString(refereeCategoryId);
            refereeCategoryEntity = refereeCategoryJpaRepository.findById(refereeCategoryUUID)
                    .orElseThrow(() ->
                            new EntityNotFoundException("Referee category with id = " + refereeCategoryId + " not found")
                    );
        }

        var refereeUUID = UUID.fromString(refereeId);

        var refereeEntity = refereeJpaRepository.findById(refereeUUID)
                .orElseThrow(() ->
                        new EntityNotFoundException("Referee with id = " + refereeId + " not found")
                );
        refereeEntity.setCategoryEntity(refereeCategoryEntity);
        var updatedRefereeEntity = refereeJpaRepository.save(refereeEntity);

        return RefereeEntityToDomainConverter.convert(updatedRefereeEntity);
    }
}
