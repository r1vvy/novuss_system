package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class RefereeCategoryDomainToEntityConverter {

    public static RefereeCategoryEntity convert(RefereeCategory refereeCategory) {
        var referees = refereeCategory.referees() == null ?
                new HashSet<RefereeEntity>() :
                refereeCategory.referees().stream()
                        .map(RefereeDomainToEntityConverter::convert)
                        .collect(Collectors.toSet());

        return RefereeCategoryEntity.builder()
                .id(refereeCategory.id())
                .title(refereeCategory.title())
                .referees(referees)
                .createdAt(refereeCategory.createdAt())
                .updatedAt(refereeCategory.updatedAt())
                .build();
    }
}
