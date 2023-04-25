package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.repository.entity.SportsClassEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SportsClassDomainToEntityConverter {
    public static SportsClassEntity convert(SportsClass sportsClass) {
        return SportsClassEntity.builder()
                .id(UUID.fromString(sportsClass.id()))
                .title(sportsClass.title())
                .createdAt(sportsClass.createdAt())
                .updatedAt(sportsClass.updatedAt())
                .build();
    }
}
