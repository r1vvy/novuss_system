package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.person.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.dto.request.CreatePersonInRequest;
import com.novuss.restfulservice.in.dto.request.UpdatePersonInRequest;
import com.novuss.restfulservice.in.dto.response.PersonResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.person.CreatePersonInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.person.PersonDomainToPersonResponseConverter;
import com.novuss.restfulservice.in.util.converter.person.UpdatePersonInRequestToDomainConverter;
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
@RequestMapping("/api/v1/people")
@AllArgsConstructor
@Slf4j
@Validated
public class PersonController {
    private final SavePersonUseCase savePersonUseCase;
    private final GetPersonByIdUseCase getPersonByIdUseCase;
    private final GetAllPeopleUseCase getAllPeople;
    private final UpdatePersonByIdUseCase updatePersonByIdUseCase;
    private final DeletePersonByIdUseCase deletePersonByIdUseCase;

    @PostMapping
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<PersonResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestBody CreatePersonInRequest request) {
        log.info("Received create person request: {}", request);

        var person = CreatePersonInRequestToDomainConverter.convert(request);
        var createdPerson = savePersonUseCase.save(person);
        var response = PersonDomainToPersonResponseConverter.convert(createdPerson);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping("/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<PersonResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                              @RequestParam("id") String id) {
        log.info("Received get person by id request: {}", id);
        var person = getPersonByIdUseCase.getById(id);
        var response = PersonDomainToPersonResponseConverter.convert(person);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<Page<PersonResponse>> getAllByPage(
            @RequestHeader("Authorization") String authorizationHeader,
            @Min(value = 0, message = "Minimum page value is 0")
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @Range(min = MIN_PAGE_SIZE, max = MAX_PAGE_SIZE, message = "Page size must be between " + MIN_PAGE_SIZE + " and " + MAX_PAGE_SIZE)
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size
    ) {
        var pageable = PageRequest.of(page, size);

        log.info("Received get all people by page request: {}", pageable);
        var people = getAllPeople.getAllByPage(pageable);

        return ResponseEntity.ok(people.map(PersonDomainToPersonResponseConverter::convert)
        );
    }

    @PutMapping("/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<PersonResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                 @PathVariable("id") String id,
                                                 @RequestBody UpdatePersonInRequest request) {
        log.info("Received update person request: {}", request);
        log.info("Update person wth id: {}", id);
        var person = UpdatePersonInRequestToDomainConverter.convert(request);
        var updatedPerson = updatePersonByIdUseCase.updateById(id, person);
        var response = PersonDomainToPersonResponseConverter.convert(updatedPerson);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @PathVariable("id") String id) {
        log.info("Received delete person request: {}", id);
        deletePersonByIdUseCase.deleteById(id);
    }
}
