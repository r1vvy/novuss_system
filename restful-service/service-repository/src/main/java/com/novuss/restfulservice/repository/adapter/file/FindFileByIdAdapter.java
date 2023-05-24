package com.novuss.restfulservice.repository.adapter.file;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.file.FindFileByIdPort;
import com.novuss.restfulservice.domain.FileDomain;
import com.novuss.restfulservice.repository.converter.FileEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindFileByIdAdapter implements FindFileByIdPort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public FileDomain findById(String id) {
        var uuid = UUID.fromString(id);
        var fileEntity = fileJpaRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("File not found with id: " + id)
        );
        var file = new File(fileEntity.getLocation());

        if(file.exists()) {
            try {
                byte[] fileData = Files.readAllBytes(file.toPath());

                var fileDomain = FileEntityToDomainConverter.convert(fileEntity);

                return fileDomain.toBuilder()
                        .content(fileData)
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("Error reading file: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("File not found: " + fileEntity.getLocation());
        }
    }
}



