package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.file.FindFileByIdUseCase;
import com.novuss.restfulservice.core.port.in.file.SaveFileUseCase;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.dto.response.FileResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.file.FileDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.file.MultipartFileToFileDomainConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    private final FindFileByIdUseCase findFileByIdUseCase;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
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
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping(value = "/download/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<Resource> download(@UUID @PathVariable("id") String id) {
        var file = findFileByIdUseCase.findById(id);

        var resource = new ByteArrayResource(file.content());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.title() + "\"")
                .contentLength(file.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
