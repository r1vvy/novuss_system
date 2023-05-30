package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.competition.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionInRequest;
import com.novuss.restfulservice.in.dto.response.CompetitionResponse;
import com.novuss.restfulservice.in.dto.response.FileResponse;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.competition.CompetitionDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.competition.CreateCompetitionInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.competition.UpdateCompetitionInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.file.FileDomainToResponseConverter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.novuss.restfulservice.in.util.PagingUtils.*;

@RestController
@RequestMapping("/api/v1/competitions")
@AllArgsConstructor
@Slf4j
@Validated
public class CompetitionController {
    private final SaveCompetitionUseCase saveCompetitionUseCase;
    private final FindCompetitionByIdUseCase getCompetitionByIdUseCase;
    private final GetCompetitionFilesByCompIdUseCase getCompetitionFilesByCompIdUseCase;
    private final FindAllCompetitionsUseCase findAllCompetitionsUseCase;
    private final UpdateCompetitionByIdUseCase updateCompetitionByIdUseCase;
    private final DeleteCompetitionByIdUseCase deleteCompetitionByIdUseCase;

    @PostMapping
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestBody CreateCompetitionInRequest request) {
        log.info("Received create competition request: {}", request);

        var competition = CreateCompetitionInRequestToDomainConverter.convert(request);
        var createdCompetition = saveCompetitionUseCase.save(competition);
        var response = CompetitionDomainToResponseConverter.convert(createdCompetition);

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
    public ResponseEntity<CompetitionResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                   @UUID @PathVariable("id") String id) {
        log.info("Received get competition by id request: {}", id);
        var competition = getCompetitionByIdUseCase.findById(id);
        var response = CompetitionDomainToResponseConverter.convert(competition);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<Page<CompetitionResponse>> getAll(
            @RequestHeader("Authorization") String authorizationHeader,
            @Min(value = 0, message = "Minimum page value is 0")
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @Range(min = MIN_PAGE_SIZE, max = MAX_PAGE_SIZE, message = "Page size must be between " + MIN_PAGE_SIZE + " and " + MAX_PAGE_SIZE)
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size
    ) {
        log.info("Received get all competitions request");
        var pageable = PageRequest.of(page, size);

        var competitions = findAllCompetitionsUseCase.findAllByPage(pageable);
        var response = competitions.map(CompetitionDomainToResponseConverter::convert);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/files")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<List<FileResponse>> getAllFiles(@RequestHeader("Authorization") String authorizationHeader,
                                                          @UUID @PathVariable("id") String id) {
        log.info("Received get all files for competition {} request", id);
        var files = getCompetitionFilesByCompIdUseCase.getCompetitionFilesByCompId(id);
        var response = files.stream()
                .map(FileDomainToResponseConverter::convert)
                .toList();

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                      @UUID @PathVariable("id") String id,
                                                     @Valid @RequestBody UpdateCompetitionInRequest request) {
        log.info("Received update competition request: {}", request);

        var competition = UpdateCompetitionInRequestToDomainConverter.convert(request);
        var updatedCompetition = updateCompetitionByIdUseCase.update(id, competition);
        var response = CompetitionDomainToResponseConverter.convert(updatedCompetition);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{competitionId}/category/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionResponse> updateCategory(@RequestHeader("Authorization") String authorizationHeader,
                                                              @UUID @PathVariable("competitionId") String competitionId,
                                                              @UUID @PathVariable(value = "id", required = false) String categoryId
    ) {
        log.info("Received update competition {} location {} request", competitionId, categoryId);

        var updatedCompetition = updateCompetitionByIdUseCase.updateCategoryById(competitionId, categoryId);
        var response = CompetitionDomainToResponseConverter.convert(updatedCompetition);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{competitionId}/location/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionResponse> updateLocation(@RequestHeader("Authorization") String authorizationHeader,
                                                              @UUID @PathVariable("competitionId") String competitionId,
                                                              @UUID @PathVariable(value = "id", required = false) String locationId
    ) {
        log.info("Received update competition {} location {} request", competitionId, locationId);

        var updatedCompetition = updateCompetitionByIdUseCase.updateLocationById(competitionId, locationId);
        var response = CompetitionDomainToResponseConverter.convert(updatedCompetition);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{competitionId}/files/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    @ResponseStatus(HttpStatus.OK)
    public void updateFiles(@RequestHeader("Authorization") String authorizationHeader,
                                                              @UUID @PathVariable("competitionId") String competitionId,
                                                              @UUID @PathVariable(value = "id", required = false) String fileId
    ) {
        log.info("Received add competition {} files {} request", competitionId, fileId);

        updateCompetitionByIdUseCase.addFileById(competitionId, fileId);
    }

    @DeleteMapping("/{id}")
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @UUID @PathVariable("id") String id) {
        log.info("Received delete competition request: {}", id);

        deleteCompetitionByIdUseCase.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
