package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.person.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.converter.person.CreatePersonInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.person.UpdatePersonInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreatePersonInRequest;
import com.novuss.restfulservice.in.dto.request.UpdatePersonInRequest;
import com.novuss.restfulservice.in.converter.person.PersonDomainToPersonInResponseConverter;
import com.novuss.restfulservice.in.dto.response.PersonInResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor
@Slf4j
public class PersonController {
    private final SavePersonUseCase savePersonUseCase;
    private final GetPersonByIdUseCase getPersonByIdUseCase;
    private final GetAllPeopleUseCase getAllPeople;
    private final UpdatePersonByIdUseCase updatePersonByIdUseCase;
    private final DeletePersonByIdUseCase deletePersonByIdUseCase;

    @PostMapping
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<PersonInResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestBody CreatePersonInRequest request) {
        log.info("Received create person request: {}", request);

        var person = CreatePersonInRequestToDomainConverter.convert(request);
        var createdPerson = savePersonUseCase.save(person);
        var response = PersonDomainToPersonInResponseConverter.convert(createdPerson);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<PersonInResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                @RequestParam("id") String id) {
        log.info("Received get person by id request: {}", id);
        var person = getPersonByIdUseCase.getById(id);
        var response = PersonDomainToPersonInResponseConverter.convert(person);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonInResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all people request");
        var people = getAllPeople.getAll();

        return ResponseEntity.ok(people.stream()
                .map(PersonDomainToPersonInResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<PersonInResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestParam("id") String id,
                                                   @RequestBody UpdatePersonInRequest request) {
        log.info("Received update person request: {}", request);
        var person = UpdatePersonInRequestToDomainConverter.convert(request);
        var updatedPerson = updatePersonByIdUseCase.updateById(id, person);
        var response = PersonDomainToPersonInResponseConverter.convert(updatedPerson);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestParam("id") String id) {
        log.info("Received delete person request: {}", id);
        deletePersonByIdUseCase.deleteById(id);
    }
}
