package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RefereeCategoryDomainToEntityConverter {

    public static RefereeCategoryEntity convert(RefereeCategory category) {
        return RefereeCategoryEntity.builder()
                .id(category.id())
                .title(category.title())
                .createdAt(category.createdAt())
                .updatedAt(category.updatedAt())
                .build();
    }
}
