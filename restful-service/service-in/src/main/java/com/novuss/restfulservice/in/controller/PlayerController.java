package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.licence.*;
import com.novuss.restfulservice.core.port.in.player.*;
import com.novuss.restfulservice.in.converter.licence.CreateLicenceInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.licence.LicenceDomainToLicenceResponseConverter;
import com.novuss.restfulservice.in.converter.licence.UpdateLicenceInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.player.CreatePlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.player.PlayerDomainToPlayerResponseConverter;
import com.novuss.restfulservice.in.converter.player.UpdatePlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateLicenceInRequest;
import com.novuss.restfulservice.in.dto.request.CreatePlayerInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateLicenceInRequest;
import com.novuss.restfulservice.in.dto.request.UpdatePlayerInRequest;
import com.novuss.restfulservice.in.dto.response.LicenceResponse;
import com.novuss.restfulservice.in.dto.response.PlayerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clubs")
@AllArgsConstructor
@Slf4j
public class PlayerController {
    private final SavePlayerUseCase savePlayerUseCase;
    private final GetPlayerByIdUseCase getPlayerByIdUseCase;
    private final GetAllPlayersUseCase getAllPlayersUseCase;
    private final UpdatePlayerByIdUseCase updatePlayerByIdUseCase;
    private final DeletePlayerByIdUseCase deletePlayerByIdUseCase;

    @PostMapping
    public ResponseEntity<PlayerResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestBody CreatePlayerInRequest request) {
        log.info("Received create player request: {}", request);

        var player = CreatePlayerInRequestToDomainConverter.convert(request);
        var createdPlayer = savePlayerUseCase.save(player);
        var response = PlayerDomainToPlayerResponseConverter.convert(createdPlayer);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<PlayerResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                               @RequestParam("id") String id) {
        log.info("Received get player by id request: {}", id);
        var player = getPlayerByIdUseCase.getById(id);
        var response = PlayerDomainToPlayerResponseConverter.convert(player);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all players request");
        var players = getAllPlayersUseCase.getAll();

        return ResponseEntity.ok(players.stream()
                .map(PlayerDomainToPlayerResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<PlayerResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                  @RequestParam("id") String id,
                                                  @RequestBody UpdatePlayerInRequest request) {
        log.info("Received update player request: {}", request);
        var player = UpdatePlayerInRequestToDomainConverter.convert(request);
        var updatedPlayer = updatePlayerByIdUseCase.updatePlayerById(player, id);
        var response = PlayerDomainToPlayerResponseConverter.convert(updatedPlayer);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete player request: {}", id);
        deletePlayerByIdUseCase.deleteById(id);
    }
}
