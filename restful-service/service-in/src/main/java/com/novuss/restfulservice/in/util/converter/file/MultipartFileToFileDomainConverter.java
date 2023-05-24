package com.novuss.restfulservice.in.util.converter.file;

import com.novuss.restfulservice.domain.FileDomain;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class MultipartFileToFileDomainConverter {
    public static FileDomain convert(MultipartFile multipartFile) throws IOException {
        try (var inputStream = new BufferedInputStream(multipartFile.getInputStream())) {
            var outputStream = new ByteArrayOutputStream();
            // TODO: move to config
            var buffer = new byte[4096];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            var content = outputStream.toByteArray();

            return FileDomain.builder()
                    .title(multipartFile.getOriginalFilename())
                    .type(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .content(content)
                    .build();
        }
    }
}
