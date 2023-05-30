package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.competition_player.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionPlayerInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionPlayerInRequest;
import com.novuss.restfulservice.in.dto.response.CompetitionPlayerResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.competition_player.CompetitionPlayerDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.competition_player.CreateCompetitionPlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.competition_player.UpdateCompetitionPlayerInRequestToDomainConverter;
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


    @PostMapping("/{playerId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionPlayerResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                            @UUID @PathVariable("competitionId") String competitionId,
                                                            @UUID @PathVariable("playerId") String playerId,
                                                            @Valid @RequestBody CreateCompetitionPlayerInRequest request) {
        log.info("Received create competition_player request: {}", request);

        var competitionPlayer = CreateCompetitionPlayerInRequestToDomainConverter.convert(playerId, request);
        var createdCompetitionPlayer = saveCompetitionPlayerUseCase.save(competitionId, competitionPlayer);
        var response = CompetitionPlayerDomainToResponseConverter.convert(createdCompetitionPlayer);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.playerId())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping("/{playerId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionPlayerResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                         @UUID @PathVariable("competitionId") String competitionId,
                                                         @UUID @PathVariable("playerId") String playerId) {
        log.info("Received get competition player by id request: {}", playerId);
        var competitionPlayer = getCompetitionPlayerByIdUseCase.findById(competitionId, playerId);
        var response = CompetitionPlayerDomainToResponseConverter.convert(competitionPlayer);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<List<CompetitionPlayerResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader,
                                                                  @UUID @PathVariable("competitionId") String competitionId) {
        log.info("Received get all competition players by competition id request");
        var competitionPlayers = getAllCompetitionPlayersByCompetitionId.getAllByCompetitionId(competitionId);

        var response = competitionPlayers.stream()
                .map(CompetitionPlayerDomainToResponseConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{playerId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionPlayerResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                            @UUID @PathVariable("competitionId") String competitionId,
                                                            @UUID @PathVariable("playerId") String playerId,
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

    @DeleteMapping("/{playerId}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @UUID @PathVariable("competitionId") String competitionId,
                                       @UUID @PathVariable("playerId") String playerId) {
        log.info("Received delete competition player request: {}", playerId);

        deleteCompetitionPlayerByIdUseCase.deleteById(competitionId, playerId);

        return ResponseEntity.noContent().build();
    }
}
