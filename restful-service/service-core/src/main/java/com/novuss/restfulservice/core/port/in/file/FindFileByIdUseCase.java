package com.novuss.restfulservice.core.port.in.file;

import com.novuss.restfulservice.domain.FileDomain;

public interface FindFileByIdUseCase {
    FileDomain findById(String id);
}
