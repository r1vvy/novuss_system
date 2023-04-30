package com.novuss.restfulservice.core.service.file;

import com.novuss.restfulservice.core.port.in.file.SaveFileUseCase;
import com.novuss.restfulservice.core.port.out.file.SaveFilePort;
import com.novuss.restfulservice.core.util.FileUtils;
import com.novuss.restfulservice.domain.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveFileService implements SaveFileUseCase {
    private final SaveFilePort saveFilePort;
    @Override
    public File save(File file) {
        if(!FileUtils.isAllowedType(file.type()) || !FileUtils.isAllowedSize(file.size())) {
            log.error("File type or size not allowed");
            throw new RuntimeException("File type or size not allowed");
        }

        return saveFilePort.save(file);
    }
}
