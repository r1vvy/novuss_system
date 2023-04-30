package com.novuss.restfulservice.in.util.converter.file;

import com.novuss.restfulservice.domain.File;
import com.novuss.restfulservice.in.dto.response.FileResponse;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class FileDomainToResponseConverter {
    public static FileResponse convert(File file) {
        return FileResponse.builder()
                .id(file.id().toString())
                .title(file.title())
                .type(file.type())
                .location(file.location())
                .size(file.size())
                .createdAt(file.createdAt())
                .updatedAt(file.updatedAt())
                .build();
    }
}
