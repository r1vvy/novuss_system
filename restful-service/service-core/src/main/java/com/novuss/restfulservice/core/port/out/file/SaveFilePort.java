package com.novuss.restfulservice.core.port.out.file;

import com.novuss.restfulservice.domain.File;

public interface SaveFilePort {
    File save(File file);
}
