package com.novuss.restfulservice.repository.adapter.file;

import com.novuss.restfulservice.core.port.out.file.SaveFilePort;
import com.novuss.restfulservice.core.util.FileUtils;
import com.novuss.restfulservice.domain.File;
import com.novuss.restfulservice.repository.converter.FileDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.FileEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.FileJpaRepository;
import com.novuss.restfulservice.repository.util.FileConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveFileAdapter implements SaveFilePort {
    private final FileJpaRepository fileJpaRepository;
    private final FileConfig fileConfig;
    private final FileUtils fileUtils;
    @Override
    public File save(File file) {
        var uploadDir = FileConfig.generateUploadPath();
        log.debug("Upload directory: {}", uploadDir);
        var fileExtension = fileUtils.getFileExtension(file.type());
        var uniqueFileName = fileConfig.generateUniqueFileName(fileExtension);
        var fileLocation = uploadDir + "/" + uniqueFileName;

        try {
            var filePath = Paths.get(fileLocation);
            Files.write(filePath, file.content(), StandardOpenOption.CREATE_NEW);

        } catch (IOException e) {
            log.error("Error saving file: {}", e.getMessage());
            throw new RuntimeException("Error saving file: " + e.getMessage());
        }

        var fileEntity = FileDomainToEntityConverter.convert(file);
        fileEntity.setLocation(fileLocation);
        var savedEntity = fileJpaRepository.save(fileEntity);
        log.info("File entity saved successfully: {}", savedEntity);

        return FileEntityToDomainConverter.convert(savedEntity);
    }
}
