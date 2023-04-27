package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.competitionCategory.*;
import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.in.converter.club.ClubDomainToClubResponseConverter;
import com.novuss.restfulservice.in.converter.club.CreateClubInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.club.UpdateClubInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.competitionCategory.CompetitionCategoryDomainToResponseConverter;
import com.novuss.restfulservice.in.converter.competitionCategory.CreateCompetitionCategoryInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.competitionCategory.UpdateCompetitionCategoryInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateClubInRequest;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionCategoryInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateClubInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionCategoryInRequest;
import com.novuss.restfulservice.in.dto.response.ClubResponse;
import com.novuss.restfulservice.in.dto.response.CompetitionCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/competition-categories")
@AllArgsConstructor
@Slf4j
public class CompetitionCategoryController {
    private final SaveCompetitionCategoryUseCase saveCompetitionCategoryUseCase;
    private final FindCompetitionCategoryByIdUseCase getCompetitionCategoryByIdUseCase;
    private final GetAllCompetitionCategoriesUseCase getAllCompetitionCategories;
    private final UpdateCompetitionCategoryByIdUseCase updateCompetitionCategoryByIdUseCase;
    private final DeleteCompetitionCategoryByIdUseCase deleteCompetitionCategoryByIdUseCase;

    @PostMapping
    public ResponseEntity<CompetitionCategoryResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                              @RequestBody CreateCompetitionCategoryInRequest request) {
        log.info("Received create competitionCategory request: {}", request);

        var competitionCategory = CreateCompetitionCategoryInRequestToDomainConverter.convert(request);
        var createdCompetitionCategory = saveCompetitionCategoryUseCase.save(competitionCategory);
        var response = CompetitionCategoryDomainToResponseConverter.convert(createdCompetitionCategory);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CompetitionCategoryResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                            @RequestParam("id") String id) {
        log.info("Received get competition category by id request: {}", id);
        var club = getCompetitionCategoryByIdUseCase.findById(id);
        var response = CompetitionCategoryDomainToResponseConverter.convert(club);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompetitionCategoryResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all competition categories request");
        var clubs = getAllCompetitionCategories.getAll();

        return ResponseEntity.ok(clubs.stream()
                .map(CompetitionCategoryDomainToResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<CompetitionCategoryResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                               @RequestParam("id") String id,
                                               @RequestBody UpdateCompetitionCategoryInRequest request) {
        log.info("Received update competition categories request: {}", request);
        var competitionCategory = UpdateCompetitionCategoryInRequestToDomainConverter.convert(request);
        var updatedCompetitionCategory = updateCompetitionCategoryByIdUseCase.updateById(competitionCategory, id);
        var response = CompetitionCategoryDomainToResponseConverter.convert(updatedCompetitionCategory);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete competition category request: {}", id);
        deleteCompetitionCategoryByIdUseCase.deleteById(id);
    }

}
