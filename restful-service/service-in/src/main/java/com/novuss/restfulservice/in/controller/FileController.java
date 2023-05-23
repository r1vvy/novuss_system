package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.file.SaveFileUseCase;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.file.FileDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.file.MultipartFileToFileDomainConverter;
import com.novuss.restfulservice.in.dto.response.FileResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
@AllArgsConstructor
@Slf4j
@Validated
public class FileController {
    private final SaveFileUseCase saveFileUseCase;
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @RequiresAuthority(UserRole.EVENT_MANAGER)
    public ResponseEntity<FileResponse> save(@RequestHeader("Authorization") String authorizationHeader,
                                             @RequestParam("file") MultipartFile multipartFile,
                                             @ModelAttribute("title") String title) throws IOException {
        log.info("Received create file request: {}", title);
        var file = MultipartFileToFileDomainConverter.convert(multipartFile);
        file = file.toBuilder()
                .title(title)
                .build();
        var savedFile = saveFileUseCase.save(file);

        var response = FileDomainToResponseConverter.convert(savedFile);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    // TODO: download
}
