package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.competition_category.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.competition_category.CompetitionCategoryDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.competition_category.CreateCompetitionCategoryInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.competition_category.UpdateCompetitionCategoryInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionCategoryInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionCategoryInRequest;
import com.novuss.restfulservice.in.dto.response.CompetitionCategoryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/competitions/categories")
@AllArgsConstructor
@Slf4j
@Validated
public class CompetitionCategoryController {
    private final SaveCompetitionCategoryUseCase saveCompetitionCategoryUseCase;
    private final FindCompetitionCategoryByIdUseCase getCompetitionCategoryByIdUseCase;
    private final GetAllCompetitionCategoriesUseCase getAllCompetitionCategories;
    private final UpdateCompetitionCategoryByIdUseCase updateCompetitionCategoryByIdUseCase;
    private final DeleteCompetitionCategoryByIdUseCase deleteCompetitionCategoryByIdUseCase;

    @PostMapping
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionCategoryResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                              @Valid @RequestBody CreateCompetitionCategoryInRequest request) {
        log.info("Received create competition_category request: {}", request);

        var competitionCategory = CreateCompetitionCategoryInRequestToDomainConverter.convert(request);
        var createdCompetitionCategory = saveCompetitionCategoryUseCase.save(competitionCategory);
        var response = CompetitionCategoryDomainToResponseConverter.convert(createdCompetitionCategory);

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
    public ResponseEntity<CompetitionCategoryResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                           @UUID @PathVariable("id") String id) {
        log.info("Received get competition category by id request: {}", id);
        var club = getCompetitionCategoryByIdUseCase.findById(id);
        var response = CompetitionCategoryDomainToResponseConverter.convert(club);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequiresAuthority({UserRole.EVENT_MANAGER, UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<List<CompetitionCategoryResponse>> getAll(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        log.info("Received get all competition categories request");
        var clubs = getAllCompetitionCategories.getAll();

        return ResponseEntity.ok(clubs.stream()
                .map(CompetitionCategoryDomainToResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<CompetitionCategoryResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                              @UUID @PathVariable("id") String id,
                                                              @Valid @RequestBody UpdateCompetitionCategoryInRequest request) {
        log.info("Received update competition categories request: {}", request);
        var competitionCategory = UpdateCompetitionCategoryInRequestToDomainConverter.convert(request);
        var updatedCompetitionCategory = updateCompetitionCategoryByIdUseCase.updateById(competitionCategory, id);
        var response = CompetitionCategoryDomainToResponseConverter.convert(updatedCompetitionCategory);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @UUID @PathVariable("id") String id) {
        log.info("Received delete competition category request: {}", id);
        deleteCompetitionCategoryByIdUseCase.deleteById(id);
    }

}
