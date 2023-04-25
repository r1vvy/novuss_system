package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.GetLicenceByIdUseCase;
import com.novuss.restfulservice.core.port.in.licence.DeleteLicenceByIdUseCase;
import com.novuss.restfulservice.core.port.in.licence.GetAllLicencesUseCase;
import com.novuss.restfulservice.core.port.in.licence.SaveLicenceUseCase;
import com.novuss.restfulservice.core.port.in.licence.UpdateLicenceByIdUseCase;
import com.novuss.restfulservice.in.converter.licence.CreateLicenceInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.licence.LicenceDomainToLicenceResponseConverter;
import com.novuss.restfulservice.in.converter.licence.UpdateLicenceInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.person.PersonDomainToPersonInResponseConverter;
import com.novuss.restfulservice.in.converter.person.UpdatePersonInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateLicenceInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateLicenceInRequest;
import com.novuss.restfulservice.in.dto.request.UpdatePersonInRequest;
import com.novuss.restfulservice.in.dto.response.LicenceResponse;
import com.novuss.restfulservice.in.dto.response.PersonInResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/licences")
@AllArgsConstructor
@Slf4j
public class LicenceController {
    private final SaveLicenceUseCase saveLicenceUseCase;
    private final GetLicenceByIdUseCase getLicenceByIdUseCase;
    private final GetAllLicencesUseCase getAllLicences;
    private final UpdateLicenceByIdUseCase updateLicenceByIdUseCase;
    private final DeleteLicenceByIdUseCase deleteLicenceByIdUseCase;

    @PostMapping
    public ResponseEntity<LicenceResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                  @RequestBody CreateLicenceInRequest request) {
        log.info("Received create licence request: {}", request);

        var licence = CreateLicenceInRequestToDomainConverter.convert(request);
        var createdLicence = saveLicenceUseCase.save(licence);
        var response = LicenceDomainToLicenceResponseConverter.convert(createdLicence);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<LicenceResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                               @RequestParam("id") String id) {
        log.info("Received get licence by id request: {}", id);
        var licence = getLicenceByIdUseCase.getById(id);
        var response = LicenceDomainToLicenceResponseConverter.convert(licence);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LicenceResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all licences request");
        var licences = getAllLicences.getALl();

        return ResponseEntity.ok(licences.stream()
                .map(LicenceDomainToLicenceResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<LicenceResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestParam("id") String id,
                                                   @RequestBody UpdateLicenceInRequest request) {
        log.info("Received update licence request: {}", request);
        var licence = UpdateLicenceInRequestToDomainConverter.convert(request);
        var updatedLicence = updateLicenceByIdUseCase.updateLicenceById(licence, id);
        var response = LicenceDomainToLicenceResponseConverter.convert(updatedLicence);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete licence request: {}", id);
        deleteLicenceByIdUseCase.deleteLicenceById(id);
    }

}
