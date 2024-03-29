package com.novuss.restfulservice.repository.adapter.referee_category;

import com.novuss.restfulservice.core.port.out.referee_category.FindRefereeCategoryByIdPort;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.converter.RefereeCategoryEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindRefereeCategoryByIdAdapter implements FindRefereeCategoryByIdPort {
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;

    @Override
    public Optional<RefereeCategory> findById(String id) {
        var refereeCategoryEntity = refereeCategoryJpaRepository.findById(UUID.fromString(id));

        return refereeCategoryEntity.map(RefereeCategoryEntityToDomainConverter::convert);
    }
}
