package com.novuss.restfulservice.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileUtils {
    public static final long MAX_FILE_SIZE_IN_MB = 50;
    public static final List<String> ALLOWED_TYPES = List.of(
            "image/png",
            "image/jpeg",
            "image/gif",
            "image/svg+xml",
            "application/pdf",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    public static boolean isAllowedType(String type) {
        return ALLOWED_TYPES.contains(type);
    }
    public static boolean isAllowedSize(long size) {
        return size <= MAX_FILE_SIZE_IN_MB * 1024 * 1024;
    }
    public String getFileExtension(String contentType) {
        switch(contentType) {
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return "xlsx";
            case "application/vnd.ms-excel":
                return "xls";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return "docx";
            default:
                return contentType.split("/")[1];
        }
    }
}
