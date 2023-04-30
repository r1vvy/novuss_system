package com.novuss.restfulservice.core.port.in.file;

import com.novuss.restfulservice.domain.File;

public interface SaveFileUseCase {
    File save(File file);
}
