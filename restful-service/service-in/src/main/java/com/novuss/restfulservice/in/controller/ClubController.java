package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.club.UpdateClubLocationByIdUseCase;
import com.novuss.restfulservice.core.port.in.club.*;
import com.novuss.restfulservice.in.converter.club.ClubDomainToClubResponseConverter;
import com.novuss.restfulservice.in.converter.club.CreateClubInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.club.UpdateClubInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateClubInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateClubInRequest;
import com.novuss.restfulservice.in.dto.response.ClubResponse;
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
public class ClubController {
    private final SaveClubUseCase saveClubUseCase;
    private final FindClubByIdUseCase getClubByIdUseCase;
    private final GetAllClubsUseCase getAllClubs;
    private final UpdateClubByIdUseCase updateClubByIdUseCase;
    private final UpdateClubLocationByIdUseCase updateClubLocationByIdUseCase;
    private final UpdateClubContactPersonByIdUseCase updateClubContactPersonByIdUseCase;
    private final DeleteClubByIdUseCase deleteClubByIdUseCase;
    @PostMapping
    public ResponseEntity<ClubResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                               @RequestBody CreateClubInRequest request) {
        log.info("Received create club request: {}", request);

        var club = CreateClubInRequestToDomainConverter.convert(request);
        var createdClub = saveClubUseCase.save(club);
        var response = ClubDomainToClubResponseConverter.convert(createdClub);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ClubResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                               @RequestParam("id") String id) {
        log.info("Received get club by id request: {}", id);
        var club = getClubByIdUseCase.findById(id);
        var response = ClubDomainToClubResponseConverter.convert(club);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClubResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all clubs request");
        var clubs = getAllClubs.getAll();

        return ResponseEntity.ok(clubs.stream()
                .map(ClubDomainToClubResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<ClubResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                  @RequestParam("id") String id,
                                                  @RequestBody UpdateClubInRequest request) {
        log.info("Received update club request: {}", request);
        var club = UpdateClubInRequestToDomainConverter.convert(request);
        var updatedClub = updateClubByIdUseCase.updateById(club, id);
        var response = ClubDomainToClubResponseConverter.convert(updatedClub);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<ClubResponse> updateLocation(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String clubId,
                                                  @RequestParam(value = "id", required = false) String locationId) {
        log.info("Received update club location request: {}", locationId);
        var updatedClub = updateClubLocationByIdUseCase.updateClubLocationById(clubId, locationId);
        var response = ClubDomainToClubResponseConverter.convert(updatedClub);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/contact-person")
    public ResponseEntity<ClubResponse> updateContactPerson(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String clubId,
                                                  @RequestParam(value = "id") String personId) {
        log.info("Received update club contact person request: {}", personId);
        var updatedClub = updateClubContactPersonByIdUseCase.updateClubContactPersonById(clubId, personId);
        var response = ClubDomainToClubResponseConverter.convert(updatedClub);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete club request: {}", id);
        deleteClubByIdUseCase.deleteById(id);
    }
}
