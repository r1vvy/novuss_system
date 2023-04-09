package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RefereeCategoryEntityToDomainConverter {

    public static RefereeCategory convert(RefereeCategoryEntity entity) {
        var builder = RefereeCategory.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt());

        Optional.ofNullable(entity.getReferees())
                .ifPresent(referees -> builder.referees(
                        referees.stream()
                                .map(RefereeEntityToDomainConverter::convert)
                                .collect(Collectors.toSet())
                ));

        return builder.build();
    }
}
