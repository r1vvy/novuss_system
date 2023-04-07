package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.player.SavePlayerUseCase;
import com.novuss.restfulservice.in.converter.CreatePlayerInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.PlayerDomainToCreatePlayerResponseConverter;
import com.novuss.restfulservice.in.dto.request.CreatePlayerInRequest;
import com.novuss.restfulservice.in.dto.response.CreatePlayerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/players")
@AllArgsConstructor
@Slf4j
public class PlayerController {
    private final SavePlayerUseCase savePlayerUseCase;

    @PostMapping
    public ResponseEntity<CreatePlayerResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestBody CreatePlayerInRequest request) {
        log.info("Received create player request: {}", request);
        var player = CreatePlayerInRequestToDomainConverter.convert(request);
        var createdPlayer = savePlayerUseCase.save(player);
        var responseBody = PlayerDomainToCreatePlayerResponseConverter.convert(createdPlayer);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseBody)
                .toUri();

        return ResponseEntity.created(location)
                .body(responseBody);
    }

}
