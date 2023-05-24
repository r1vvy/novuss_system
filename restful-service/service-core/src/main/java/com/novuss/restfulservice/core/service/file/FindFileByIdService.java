package com.novuss.restfulservice.core.service.file;

import com.novuss.restfulservice.core.port.in.file.FindFileByIdUseCase;
import com.novuss.restfulservice.core.port.out.file.FindFileByIdPort;
import com.novuss.restfulservice.domain.FileDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindFileByIdService implements FindFileByIdUseCase {
    private final FindFileByIdPort findFileByIdPort;
    @Override
    public FileDomain findById(String id) {
        return findFileByIdPort.findById(id);
    }
}
