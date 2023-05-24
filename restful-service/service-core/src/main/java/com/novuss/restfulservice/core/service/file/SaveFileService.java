package com.novuss.restfulservice.core.service.file;

import com.novuss.restfulservice.core.port.in.file.SaveFileUseCase;
import com.novuss.restfulservice.core.port.out.file.SaveFilePort;
import com.novuss.restfulservice.core.util.FileUtils;
import com.novuss.restfulservice.domain.FileDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveFileService implements SaveFileUseCase {
    private final SaveFilePort saveFilePort;
    @Override
    public FileDomain save(FileDomain fileDomain) {
        if(!FileUtils.isAllowedType(fileDomain.type()) || !FileUtils.isAllowedSize(fileDomain.size())) {
            log.error("FileDomain type or size not allowed");
            throw new RuntimeException("FileDomain type or size not allowed");
        }

        return saveFilePort.save(fileDomain);
    }
}
