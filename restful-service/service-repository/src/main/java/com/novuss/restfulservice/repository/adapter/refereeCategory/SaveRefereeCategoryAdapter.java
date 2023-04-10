package com.novuss.restfulservice.repository.adapter.refereeCategory;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.SaveRefereePort;
import com.novuss.restfulservice.core.port.out.refereeCategory.SaveRefereeCategoryPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.converter.*;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SaveRefereeCategoryAdapter implements SaveRefereeCategoryPort {
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;

    @Override
    public RefereeCategory save(RefereeCategory refereeCategory) {
        refereeCategoryJpaRepository.findByTitle(refereeCategory.title())
                .ifPresent(refereeCategoryEntity -> {
                    log.error("RefereeCategory with title {} already exists", refereeCategory.title());
                    throw new EntityExistsException("RefereeCategory with title " + refereeCategory.title() + " already exists");
                });

        var refereeCategoryEntity = RefereeCategoryDomainToEntityConverter.convert(refereeCategory);
        var savedRefereeCategoryEntity = refereeCategoryJpaRepository.save(refereeCategoryEntity);

        return RefereeCategoryEntityToDomainConverter.convert(savedRefereeCategoryEntity);
    }
}
