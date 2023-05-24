package com.novuss.restfulservice.in.util.converter.file;

import com.novuss.restfulservice.domain.FileDomain;
import com.novuss.restfulservice.in.dto.response.FileResponse;
import org.springframework.stereotype.Component;

@Component
public class FileDomainToResponseConverter {
    public static FileResponse convert(FileDomain fileDomain) {
        return FileResponse.builder()
                .id(fileDomain.id().toString())
                .title(fileDomain.title())
                .type(fileDomain.type())
                .location(fileDomain.location())
                .size(fileDomain.size())
                .createdAt(fileDomain.createdAt())
                .updatedAt(fileDomain.updatedAt())
                .build();
    }
}
