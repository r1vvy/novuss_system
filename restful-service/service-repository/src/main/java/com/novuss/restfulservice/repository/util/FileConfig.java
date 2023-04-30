package com.novuss.restfulservice.repository.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Slf4j
public class FileConfig {
    public static final String UPLOAD_DIR = "var/uploads/%s/%s";
    public static String generateUploadPath() {
        var currentDate = LocalDate.now();
        var year = String.valueOf(currentDate.getYear());
        var month = String.format("%02d", currentDate.getMonthValue());

        var dir = new File(String.format(UPLOAD_DIR, year, month));
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir.getPath().replace(File.separator, "/");
    }
    public String generateUniqueFileName(String fileExtension) {
        var uniqueId = UUID.randomUUID().toString();
        uniqueId = uniqueId.replace("-", "");
        uniqueId = uniqueId.substring(0, 10);

        return uniqueId + "." + fileExtension;
    }
}
