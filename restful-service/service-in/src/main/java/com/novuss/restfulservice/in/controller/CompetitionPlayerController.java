package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.competitionPlayer.*;
import com.novuss.restfulservice.in.util.converter.competitionPlayer.CompetitionPlayerDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.competitionPlayer.CreateCompetitionPlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.competitionPlayer.UpdateCompetitionPlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionPlayerInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionPlayerInRequest;
import com.novuss.restfulservice.in.dto.response.CompetitionPlayerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/competitions/{competitionId}/players")
@AllArgsConstructor
@Slf4j
@Validated
public class CompetitionPlayerController {
    private final SaveCompetitionPlayerUseCase saveCompetitionPlayerUseCase;
    private final FindCompetitionPlayerByIdUseCase getCompetitionPlayerByIdUseCase;
    private final GetAllCompetitionPlayersByCompetitionIdUseCase getAllCompetitionPlayersByCompetitionId;
    private final UpdateCompetitionPlayerByIdUseCase updateCompetitionPlayerByIdUseCase;
    private final DeleteCompetitionPlayerByIdUseCase deleteCompetitionPlayerByIdUseCase;


    @PostMapping
    public ResponseEntity<CompetitionPlayerResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                            @PathVariable("competitionId") String competitionId,
                                                            @RequestBody CreateCompetitionPlayerInRequest request) {
        log.info("Received create competitionPlayer request: {}", request);

        var competitionPlayer = CreateCompetitionPlayerInRequestToDomainConverter.convert(request);
        var createdCompetitionPlayer = saveCompetitionPlayerUseCase.save(competitionId, competitionPlayer);
        var response = CompetitionPlayerDomainToResponseConverter.convert(createdCompetitionPlayer);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.playerId())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<CompetitionPlayerResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                             @PathVariable("competitionId") String competitionId,
                                             @PathVariable("playerId") String playerId) {
        log.info("Received get competition player by id request: {}", playerId);
        var competitionPlayer = getCompetitionPlayerByIdUseCase.findById(competitionId, playerId);
        var response = CompetitionPlayerDomainToResponseConverter.convert(competitionPlayer);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompetitionPlayerResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader,
                                                                  @PathVariable("competitionId") String competitionId) {
        log.info("Received get all competition players by competition id request");
        var competitionPlayers = getAllCompetitionPlayersByCompetitionId.getAllByCompetitionId(competitionId);

        var response = competitionPlayers.stream()
                .map(CompetitionPlayerDomainToResponseConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<CompetitionPlayerResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                            @PathVariable("competitionId") String competitionId,
                                                            @PathVariable("playerId") String playerId,
                                                            @RequestBody UpdateCompetitionPlayerInRequest request) {
        log.info("Received update competition player request: {}", request);

        var competitionPlayer = UpdateCompetitionPlayerInRequestToDomainConverter.convert(request);
        var updatedCompetitionPlayer = updateCompetitionPlayerByIdUseCase.updateById(
                competitionPlayer,
                playerId,
                competitionId
        );
        var response = CompetitionPlayerDomainToResponseConverter.convert(updatedCompetitionPlayer);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                        @PathVariable("competitionId") String competitionId,
                                       @RequestParam("id") String playerId) {
        log.info("Received delete competition player request: {}", playerId);

        deleteCompetitionPlayerByIdUseCase.deleteById(competitionId, playerId);

        return ResponseEntity.noContent().build();
    }
}
