package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.repository.entity.SportsClassEntity;
import org.springframework.stereotype.Component;

@Component
public class SportsClassEntityToDomainConverter {

    public static SportsClass convert(SportsClassEntity entity) {
        return SportsClass.builder()
                .id(entity.getId().toString())
                .title(entity.getTitle())
                .build();
    }
}
