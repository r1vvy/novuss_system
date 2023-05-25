package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.club.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.dto.request.CreateClubInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateClubInRequest;
import com.novuss.restfulservice.in.dto.response.ClubResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.club.ClubDomainToClubResponseConverter;
import com.novuss.restfulservice.in.util.converter.club.CreateClubInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.club.UpdateClubInRequestToDomainConverter;
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
@RequestMapping("/api/v1/clubs")
@AllArgsConstructor
@Slf4j
@Validated
public class ClubController {
    private final SaveClubUseCase saveClubUseCase;
    private final FindClubByIdUseCase getClubByIdUseCase;
    private final GetAllClubsByPageUseCase getAllClubsByPageUseCase;
    private final UpdateClubByIdUseCase updateClubByIdUseCase;
    private final UpdateClubLocationByIdUseCase updateClubLocationByIdUseCase;
    private final UpdateClubContactPersonByIdUseCase updateClubContactPersonByIdUseCase;
    private final DeleteClubByIdUseCase deleteClubByIdUseCase;
    @PostMapping
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<ClubResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                               @RequestBody CreateClubInRequest request) {
        log.info("Received create club request: {}", request);

        var club = CreateClubInRequestToDomainConverter.convert(request);
        var createdClub = saveClubUseCase.save(club);
        var response = ClubDomainToClubResponseConverter.convert(createdClub);

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
    public ResponseEntity<ClubResponse> getById(@RequestHeader("Authorization") String authorizationHeader,
                                                @PathVariable("id") String id) {
        log.info("Received get club by id request: {}", id);
        var club = getClubByIdUseCase.findById(id);
        var response = ClubDomainToClubResponseConverter.convert(club);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<Page<ClubResponse>> getAllByPage(
            @RequestHeader("Authorization") String authorizationHeader,
            @Min(value = 0, message = "Minimum page value is 0")
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @Range(min = MIN_PAGE_SIZE, max = MAX_PAGE_SIZE, message = "Page size must be between " + MIN_PAGE_SIZE + " and " + MAX_PAGE_SIZE)
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size
    ) {
        var pageable = PageRequest.of(page, size);

        log.info("Received get all clubs by page request: {}", pageable);
        var clubs = getAllClubsByPageUseCase.getAllByPage(pageable);

        return ResponseEntity.ok(clubs.map(ClubDomainToClubResponseConverter::convert));
    }

    @PutMapping("/{id}")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<ClubResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String id,
                                                  @RequestBody UpdateClubInRequest request) {
        log.info("Received update club request: {}", request);
        var club = UpdateClubInRequestToDomainConverter.convert(request);
        var updatedClub = updateClubByIdUseCase.updateById(club, id);
        var response = ClubDomainToClubResponseConverter.convert(updatedClub);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/location")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<ClubResponse> updateLocation(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String clubId,
                                                  @RequestParam(value = "id", required = false) String locationId) {
        log.info("Received update club location request: {}", locationId);
        var updatedClub = updateClubLocationByIdUseCase.updateClubLocationById(clubId, locationId);
        var response = ClubDomainToClubResponseConverter.convert(updatedClub);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/contact-person")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<ClubResponse> updateContactPerson(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String clubId,
                                                  @RequestParam(value = "id") String personId) {
        log.info("Received update club contact person request: {}", personId);
        var updatedClub = updateClubContactPersonByIdUseCase.updateClubContactPersonById(clubId, personId);
        var response = ClubDomainToClubResponseConverter.convert(updatedClub);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @PathVariable("id") String id) {
        log.info("Received delete club request: {}", id);
        deleteClubByIdUseCase.deleteById(id);
    }
}
