package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.FileDomain;
import com.novuss.restfulservice.repository.entity.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class FileDomainToEntityConverter {
    public static FileEntity convert(FileDomain domain) {
        return FileEntity.builder()
                .id(domain.id())
                .title(domain.title())
                .size(domain.size())
                .type(domain.type())
                .location(domain.location())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .build();
    }
}
