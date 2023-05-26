package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.competitionReferee.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionRefereeInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionRefereeInRequest;
import com.novuss.restfulservice.in.dto.response.CompetitionRefereeResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.competitionReferee.CompetitionRefereeDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.competitionReferee.CreateCompetitionRefereeInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.competitionReferee.UpdateCompetitionRefereeInRequestToDomainConverter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/competitions/{competitionId}/referees")
@AllArgsConstructor
@Slf4j
@Validated
public class CompetitionRefereeController {
    private final SaveCompetitionRefereeUseCase saveCompetitionRefereeUseCase;
    private final FindCompetitionRefereeByIdUseCase getCompetitionRefereeByIdUseCase;
    private final FindAllCompetitionRefereesByCompetitionIdUseCase findAllCompetitionRefereesByCompetitionIdUseCase;
    private final UpdateCompetitionRefereeByIdUseCase updateCompetitionRefereeByIdUseCase;
    private final DeleteCompetitionRefereeByIdUseCase deleteCompetitionRefereeByIdUseCase;

    @PostMapping("/{refereeId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionRefereeResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                             @UUID  @PathVariable("competitionId") String competitionId,
                                                             @UUID  @PathVariable("id") String refereeId,
                                                             @Valid @RequestBody CreateCompetitionRefereeInRequest request) {
        log.info("Received create competitionReferee request: {}", request);

        var competitionReferee = CreateCompetitionRefereeInRequestToDomainConverter.convert(request);
        var createdCompetitionReferee = saveCompetitionRefereeUseCase.save(competitionId, refereeId, competitionReferee);
        var response = CompetitionRefereeDomainToResponseConverter.convert(createdCompetitionReferee);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.refereeId())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping("/{refereeId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionRefereeResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                          @UUID @PathVariable("competitionId") String competitionId,
                                                          @UUID @PathVariable("refereeId") String refereeId) {
        log.info("Received get competition referee by id request: {}", refereeId);
        var competitionReferee = getCompetitionRefereeByIdUseCase.findById(competitionId, refereeId);
        var response = CompetitionRefereeDomainToResponseConverter.convert(competitionReferee);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<List<CompetitionRefereeResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader,
                                                                   @UUID @PathVariable("competitionId") String competitionId) {
        log.info("Received get all competition referees by competition id request");
        var competitionReferees = findAllCompetitionRefereesByCompetitionIdUseCase.findAllByCompetitionId(competitionId);
        var response = competitionReferees.stream()
                .map(CompetitionRefereeDomainToResponseConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{refereeId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionRefereeResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                             @UUID @PathVariable("competitionId") String competitionId,
                                                             @UUID  @PathVariable("id") String refereeId,
                                                             @Valid @RequestBody UpdateCompetitionRefereeInRequest request) {
        log.info("Received update competitionReferee request: {}", request);

        var competitionReferee = UpdateCompetitionRefereeInRequestToDomainConverter.convert(request);
        var updatedCompetitionReferee = updateCompetitionRefereeByIdUseCase.update(competitionId, refereeId, competitionReferee);
        var response = CompetitionRefereeDomainToResponseConverter.convert(updatedCompetitionReferee);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{refereeId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @UUID @PathVariable("competitionId") String competitionId,
                                       @UUID @PathVariable("refereeId") String refereeId) {
        log.info("Received delete competitionReferee request: {}", refereeId);

        deleteCompetitionRefereeByIdUseCase.delete(competitionId, refereeId);

        return ResponseEntity.noContent().build();
    }

}
