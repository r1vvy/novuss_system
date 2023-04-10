package com.novuss.restfulservice.repository.adapter.refereeCategory;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.refereeCategory.UpdateRefereeCategoryByIdPort;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.converter.RefereeCategoryDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.RefereeCategoryEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateRefereeCategoryByIdAdapter implements UpdateRefereeCategoryByIdPort {
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;

    @Override
    public RefereeCategory updateById(String id, RefereeCategory category) {
        var categoryId = UUID.fromString(id);

        var existingRefereeCategory = refereeCategoryJpaRepository.findById(categoryId)
                .orElseThrow(() -> {
                    log.error("Referee category with id {} not found", id);
                    throw new EntityNotFoundException("Referee category with id " + id + " not found");
                });

        var updatedRefereeCategoryEntity = RefereeCategoryDomainToEntityConverter.convert(category);
        updatedRefereeCategoryEntity.setId(categoryId);
        updatedRefereeCategoryEntity.setCreatedAt(existingRefereeCategory.getCreatedAt());

        return RefereeCategoryEntityToDomainConverter.convert(
                refereeCategoryJpaRepository.saveAndFlush(updatedRefereeCategoryEntity)
        );
    }
}
