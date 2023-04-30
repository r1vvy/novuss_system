package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.competition.*;
import com.novuss.restfulservice.in.converter.competition.CompetitionDomainToResponseConverter;
import com.novuss.restfulservice.in.converter.competition.CreateCompetitionInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.competition.UpdateCompetitionInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionInRequest;
import com.novuss.restfulservice.in.dto.response.CompetitionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/competitions")
@AllArgsConstructor
@Slf4j
public class CompetitionController {
    private final SaveCompetitionUseCase saveCompetitionUseCase;
    private final FindCompetitionByIdUseCase getCompetitionByIdUseCase;
    private final FindAllCompetitionsUseCase findAllCompetitionsUseCase;
    private final UpdateCompetitionByIdUseCase updateCompetitionByIdUseCase;
    private final DeleteCompetitionByIdUseCase deleteCompetitionByIdUseCase;

    @PostMapping
    public ResponseEntity<CompetitionResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestBody CreateCompetitionInRequest request) {
        log.info("Received create competition request: {}", request);

        var competition = CreateCompetitionInRequestToDomainConverter.convert(request);
        var createdCompetition = saveCompetitionUseCase.save(competition);
        var response = CompetitionDomainToResponseConverter.convert(createdCompetition);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CompetitionResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestParam("id") String id) {
        log.info("Received get competition by id request: {}", id);
        var competition = getCompetitionByIdUseCase.findById(id);
        var response = CompetitionDomainToResponseConverter.convert(competition);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all competitions request");
        var competitions = findAllCompetitionsUseCase.findAll();
        var response = competitions.stream()
                .map(CompetitionDomainToResponseConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CompetitionResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestParam("id") String id,
                                                      @RequestBody UpdateCompetitionInRequest request) {
        log.info("Received update competition request: {}", request);

        var competition = UpdateCompetitionInRequestToDomainConverter.convert(request);
        var updatedCompetition = updateCompetitionByIdUseCase.update(id, competition);
        var response = CompetitionDomainToResponseConverter.convert(updatedCompetition);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestParam("id") String id) {
        log.info("Received delete competition request: {}", id);

        deleteCompetitionByIdUseCase.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
