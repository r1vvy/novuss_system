package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.licence.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.dto.request.CreateLicenceInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateLicenceInRequest;
import com.novuss.restfulservice.in.dto.response.LicenceResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.licence.CreateLicenceInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.licence.LicenceDomainToLicenceResponseConverter;
import com.novuss.restfulservice.in.util.converter.licence.UpdateLicenceInRequestToDomainConverter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/licences")
@AllArgsConstructor
@Slf4j
@Validated
public class LicenceController {
    private final SaveLicenceUseCase saveLicenceUseCase;
    private final FindLicenceByIdUseCase findLicenceByIdUseCase;
    private final GetAllLicencesUseCase getAllLicences;
    private final UpdateLicenceByIdUseCase updateLicenceByIdUseCase;
    private final DeleteLicenceByIdUseCase deleteLicenceByIdUseCase;

    @PostMapping
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<LicenceResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                 @Valid @RequestBody CreateLicenceInRequest request) {
        log.info("Received create licence request: {}", request);

        var licence = CreateLicenceInRequestToDomainConverter.convert(request);
        var createdLicence = saveLicenceUseCase.save(licence);
        var response = LicenceDomainToLicenceResponseConverter.convert(createdLicence);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }
    @GetMapping("/{id}")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<LicenceResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                               @UUID @PathVariable("id") String id) {
        log.info("Received get licence by id request: {}", id);
        var licence = findLicenceByIdUseCase.getById(id);
        var response = LicenceDomainToLicenceResponseConverter.convert(licence);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<List<LicenceResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all licences request");
        var licences = getAllLicences.getALl();

        return ResponseEntity.ok(licences.stream()
                .map(LicenceDomainToLicenceResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<LicenceResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                  @UUID @PathVariable("id") String id,
                                                  @Valid @RequestBody UpdateLicenceInRequest request) {
        log.info("Received update licence request: {}", request);
        var licence = UpdateLicenceInRequestToDomainConverter.convert(request);
        var updatedLicence = updateLicenceByIdUseCase.updateLicenceById(licence, id);
        var response = LicenceDomainToLicenceResponseConverter.convert(updatedLicence);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @UUID @PathVariable("id") String id) {
        log.info("Received delete licence request: {}", id);
        deleteLicenceByIdUseCase.deleteLicenceById(id);
    }

}
