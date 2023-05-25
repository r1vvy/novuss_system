package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.FileDomain;
import com.novuss.restfulservice.repository.entity.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class FileEntityToDomainConverter {
    public static FileDomain convert(FileEntity entity) {
        return FileDomain.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .size(entity.getSize())
                .type(entity.getType())
                .location(entity.getLocation())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
