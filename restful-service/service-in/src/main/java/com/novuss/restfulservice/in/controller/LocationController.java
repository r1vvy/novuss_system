package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.location.*;
import com.novuss.restfulservice.in.converter.location.LocationDomainToResponseConverter;
import com.novuss.restfulservice.in.converter.location.UpdateLocationInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.location.CreateLocationInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.player.PlayerDomainToPlayerResponseConverter;
import com.novuss.restfulservice.in.dto.request.CreateLocationInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateLocationInRequest;
import com.novuss.restfulservice.in.dto.response.LocationResponse;
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
@RequestMapping("/api/v1/locations")
@AllArgsConstructor
@Slf4j
public class LocationController {
    private final SaveLocationUseCase saveLocationUseCase;
    private final GetLocationByIdUseCase getLocationByIdUseCase;
    private final GetAllLocationsUseCase getAllLocationsUseCase;
    private final UpdateLocationByIdUseCase updateLocationByIdUseCase;
    private final DeleteLocationByIdUseCase deleteLocationByIdUseCase;
    private final UpdateLocationContactPersonByIdUseCase updateLocationContactPersonByIdUseCase;

    @PostMapping
    public ResponseEntity<LocationResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestBody CreateLocationInRequest request) {
        log.info("Received create location request: {}", request);

        var locationDomain = CreateLocationInRequestToDomainConverter.convert(request);
        var createdLocation = saveLocationUseCase.save(locationDomain);
        var response = LocationDomainToResponseConverter.convert(createdLocation);

        var uriLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uriLocation)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<LocationResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestParam("id") String id) {
        log.info("Received get location by id request: {}", id);
        var location = getLocationByIdUseCase.getLocationById(id);
        var response = LocationDomainToResponseConverter.convert(location);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocationResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all locations request");
        var location = getAllLocationsUseCase.getAllLocations();

        return ResponseEntity.ok(location.stream()
                .map(LocationDomainToResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<LocationResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestParam("id") String id,
                                                      @RequestBody UpdateLocationInRequest request) {
        log.info("Received update location request: {}", request);
        var location = UpdateLocationInRequestToDomainConverter.convert(request);
        var updatedLocation = updateLocationByIdUseCase.updateLocationById(location, id);
        var response = LocationDomainToResponseConverter.convert(updatedLocation);

        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}/contact-person")
    public ResponseEntity<LocationResponse> updateContactPerson(@RequestHeader("Authorization") String authorizationHeader,
                                                     @PathVariable("id") String locationId,
                                                     @RequestParam(value = "id", required = false) String personId) {
        log.info("Received update contact person for location request: {}", locationId);
        var updatedLocation = updateLocationContactPersonByIdUseCase.updateLocationContactPersonById(locationId, personId);
        var response = LocationDomainToResponseConverter.convert(updatedLocation);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete location request: {}", id);
        deleteLocationByIdUseCase.deleteLocationById(id);
    }
}
