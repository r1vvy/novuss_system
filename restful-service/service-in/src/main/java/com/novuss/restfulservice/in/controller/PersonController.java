package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.*;
import com.novuss.restfulservice.core.port.in.person.DeletePersonByIdUseCase;
import com.novuss.restfulservice.core.port.in.person.GetAllPeopleUseCase;
import com.novuss.restfulservice.core.port.in.person.GetPersonByIdUseCase;
import com.novuss.restfulservice.core.port.in.person.SavePersonUseCase;
import com.novuss.restfulservice.in.converter.CreatePersonInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.PersonDomainToPersonDtoConverter;
import com.novuss.restfulservice.in.converter.UpdatePersonInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreatePersonInRequest;
import com.novuss.restfulservice.in.dto.request.UpdatePersonInRequest;
import com.novuss.restfulservice.in.dto.response.PersonResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/players")
@AllArgsConstructor
@Slf4j
public class PersonController {
    private final SavePersonUseCase savePersonUseCase;
    private final GetPersonByIdUseCase getPersonByIdUseCase;
    private final GetAllPeopleUseCase getAllPeople;
    private final UpdatePersonByIdUseCase updatePersonByIdUseCase;
    private final DeletePersonByIdUseCase deletePersonByIdUseCase;

    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestBody CreatePersonInRequest request) {
        log.info("Received create person request: {}", request);

        var person = CreatePersonInRequestToDomainConverter.convert(request);
        var createdPerson = savePersonUseCase.save(person);
        var response = PersonDomainToPersonDtoConverter.convert(createdPerson);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response)
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                              @PathVariable("id") String id) {
        log.info("Received get person request: {}", id);
        var person = getPersonByIdUseCase.getById(id);
        var response = PersonDomainToPersonDtoConverter.convert(person);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all people request");
        var people = getAllPeople.getAll();

        return ResponseEntity.ok(people.stream()
                .map(PersonDomainToPersonDtoConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                 @PathVariable("id") String id,
                                                 @RequestBody UpdatePersonInRequest request) {
        log.info("Received update person request: {}", request);
        var person = UpdatePersonInRequestToDomainConverter.convert(request);
        var updatedPerson = updatePersonByIdUseCase.updateById(id, person);
        var response = PersonDomainToPersonDtoConverter.convert(updatedPerson);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @PathVariable("id") String id) {
        log.info("Received delete person request: {}", id);
        deletePersonByIdUseCase.deleteById(id);
    }
}
