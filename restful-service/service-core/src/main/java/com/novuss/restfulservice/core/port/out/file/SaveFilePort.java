package com.novuss.restfulservice.core.port.out.file;

import com.novuss.restfulservice.domain.FileDomain;

public interface SaveFilePort {
    FileDomain save(FileDomain fileDomain);
}
