package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.sportsClass.*;
import com.novuss.restfulservice.in.util.converter.sportsClass.CreateSportsClassInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.sportsClass.SportsClassDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.sportsClass.UpdateSportsClassInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateSportsClassInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateSportsClassInRequest;
import com.novuss.restfulservice.in.dto.response.SportsClassResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sports-classes")
@AllArgsConstructor
@Slf4j
@Validated
public class SportsClassController {
    private final SaveSportsClassUseCase saveSportsClassUseCase;
    private final GetSportsClassByIdUseCase getSportsClassByIdUseCase;
    private final GetAllSportsClassesUseCase getAllSportsClassesUseCase;
    private final UpdateSportsClassByIdUseCase updateSportsClassByIdUseCase;
    private final DeleteSportsClassByIdUseCase deleteSportsClassByIdUseCase;

    @PostMapping
    public ResponseEntity<SportsClassResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestBody CreateSportsClassInRequest request) {
        log.info("Received create sports class request: {}", request);

        var sportsClass = CreateSportsClassInRequestToDomainConverter.convert(request);
        var createdSportsClass = saveSportsClassUseCase.save(sportsClass);
        var response = SportsClassDomainToResponseConverter.convert(createdSportsClass);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SportsClassResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                               @PathVariable("id") String id) {
        log.info("Received get sports class by id request: {}", id);
        var sportsClass = getSportsClassByIdUseCase.getById(id);
        var response = SportsClassDomainToResponseConverter.convert(sportsClass);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SportsClassResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all sports classes request");
        var sportsClasses = getAllSportsClassesUseCase.getAll();

        return ResponseEntity.ok(sportsClasses.stream()
                .map(SportsClassDomainToResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportsClassResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String id,
                                                  @RequestBody UpdateSportsClassInRequest request) {
        log.info("Received update sports class request: {}", request);
        var sportsClass = UpdateSportsClassInRequestToDomainConverter.convert(request);
        var updatedSportsClass = updateSportsClassByIdUseCase.update(sportsClass, id);
        var response = SportsClassDomainToResponseConverter.convert(updatedSportsClass);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete sports class request: {}", id);
        deleteSportsClassByIdUseCase.delete(id);
    }
}
