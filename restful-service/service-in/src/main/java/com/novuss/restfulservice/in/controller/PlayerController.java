package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.player.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.player.CreatePlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.player.PlayerDomainToPlayerResponseConverter;
import com.novuss.restfulservice.in.util.converter.player.UpdatePlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreatePlayerInRequest;
import com.novuss.restfulservice.in.dto.request.UpdatePlayerInRequest;
import com.novuss.restfulservice.in.dto.response.PlayerResponse;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.novuss.restfulservice.in.util.PagingUtils.*;

@RestController
@RequestMapping("/api/v1/players")
@AllArgsConstructor
@Slf4j
@Validated
public class PlayerController {
    private final SavePlayerUseCase savePlayerUseCase;
    private final FindPlayerByIdUseCase findPlayerByIdUseCase;
    private final GetAllPlayersByPageUseCase getAllPlayersByPageUseCase;
    private final UpdatePlayerByIdUseCase updatePlayerByIdUseCase;
    private final UpdatePlayerLicenceByIdUseCase updatePlayerLicenceByIdUseCase;
    private final UpdatePlayerClubByIdUseCase updatePlayerClubByIdUseCase;
    private final UpdatePlayerSportsClassByIdUseCase updatePlayerSportsClassByIdUseCase;
    private final DeletePlayerByIdUseCase deletePlayerByIdUseCase;

    @PostMapping
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<PlayerResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestBody CreatePlayerInRequest request) {
        log.info("Received create player request: {}", request);

        var player = CreatePlayerInRequestToDomainConverter.convert(request);
        var createdPlayer = savePlayerUseCase.save(player);
        log.debug("Created player: {}", createdPlayer);
        var response = PlayerDomainToPlayerResponseConverter.convert(createdPlayer);
        log.debug("Created player response: {}", response);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }
    @GetMapping("/{id}")
    @RequiresAuthority(UserRole.EVENT_MANAGER)
    public ResponseEntity<PlayerResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                               @PathVariable("id") String id) {
        log.info("Received get player by id request: {}", id);
        var player = findPlayerByIdUseCase.getById(id);
        var response = PlayerDomainToPlayerResponseConverter.convert(player);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<Page<PlayerResponse>> getAllByPage(
            @RequestHeader("Authorization") String authorizationHeader,
            @Min(value = 0, message = "Minimum page value is 0")
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @Range(min = MIN_PAGE_SIZE, max = MAX_PAGE_SIZE, message = "Page size must be between " + MIN_PAGE_SIZE + " and " + MAX_PAGE_SIZE)
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size
    ) {
        log.info("Received get all players request with page = {}, size = {}", page, size);
        var pageable = PageRequest.of(page, size);

        var players = getAllPlayersByPageUseCase.getAllByPage(pageable);
        var response = players.map(PlayerDomainToPlayerResponseConverter::convert);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @RequiresAuthority(UserRole.EVENT_MANAGER)
    public ResponseEntity<PlayerResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String id,
                                                  @RequestBody UpdatePlayerInRequest request) {
        log.info("Received update player request: {}", request);
        var player = UpdatePlayerInRequestToDomainConverter.convert(request);
        var updatedPlayer = updatePlayerByIdUseCase.updatePlayerById(player, id);
        var response = PlayerDomainToPlayerResponseConverter.convert(updatedPlayer);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}/licence")
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<PlayerResponse> updateLicence(@RequestHeader("Authorization") String authorizationHeader,
                                                @PathVariable("id") String playerId,
                                                @RequestParam(value = "id", required = false) String licenceId) {
        log.info("Received update licence for player request: {}", playerId);
        var updatedPlayer = updatePlayerLicenceByIdUseCase.updatePlayerLicenceById(playerId, licenceId);
        var response = PlayerDomainToPlayerResponseConverter.convert(updatedPlayer);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}/club")
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<PlayerResponse> updateClub(@RequestHeader("Authorization") String authorizationHeader,
                                                         @PathVariable("id") String playerId,
                                                         @RequestParam(value = "id", required = false) String clubId) {
        log.info("Received update club for player request: {}", playerId);
        var updatedPlayer = updatePlayerClubByIdUseCase.updatePlayerClubById(playerId, clubId);
        var response = PlayerDomainToPlayerResponseConverter.convert(updatedPlayer);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}/sports-class")
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<PlayerResponse> updateSportsClass(@RequestHeader("Authorization") String authorizationHeader,
                                                      @PathVariable("id") String playerId,
                                                      @RequestParam(value = "id", required = false) String sportsClassId) {
        log.info("Received update sports class for player request: {}", playerId);
        var updatedPlayer = updatePlayerSportsClassByIdUseCase.updatePlayerSportsClassById(playerId, sportsClassId);
        var response = PlayerDomainToPlayerResponseConverter.convert(updatedPlayer);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequiresAuthority(UserRole.ADMIN)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete player request: {}", id);
        deletePlayerByIdUseCase.deleteById(id);
    }
}
