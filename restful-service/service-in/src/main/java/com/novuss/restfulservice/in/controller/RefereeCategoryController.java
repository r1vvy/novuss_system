package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.refereeCategory.*;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.util.RequiresAuthority;
import com.novuss.restfulservice.in.util.converter.refereeCategory.CreateRefereeCategoryInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.refereeCategory.RefereeCategoryDomainToRefereeCategoryDtoConverter;
import com.novuss.restfulservice.in.util.converter.refereeCategory.RefereeCategoryDomainToRefereeCategoryResponseConverter;
import com.novuss.restfulservice.in.util.converter.refereeCategory.UpdateRefereeCategoryInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.RefereeCategoryDto;
import com.novuss.restfulservice.in.dto.request.CreateRefereeCategoryInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateRefereeCategoryInRequest;
import com.novuss.restfulservice.in.dto.response.RefereeCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/referees/categories")
@RequiredArgsConstructor
@Slf4j
@Validated
public class RefereeCategoryController {
    private final SaveRefereeCategoryUseCase saveRefereeCategoryUseCase;
    private final GetRefereeCategoryByIdUseCase getRefereeCategoryByIdUseCase;
    private final GetAllRefereeCategoriesUseCase getAllRefereeCategoriesUseCase;
    private final UpdateRefereeCategoryByIdUseCase updateRefereeCategoryByIdUseCase;
    private final DeleteRefereeCategoryByIdUseCase deleteRefereeCategoryByIdUseCase;

    @PostMapping
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<RefereeCategoryResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                          @Valid @RequestBody CreateRefereeCategoryInRequest request) {
        log.info("Received create referee category request: {}", request);

        var category = CreateRefereeCategoryInRequestToDomainConverter.convert(request);
        var createdCategory = saveRefereeCategoryUseCase.save(category);
        var response = RefereeCategoryDomainToRefereeCategoryResponseConverter.convert(createdCategory);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
    @GetMapping("/{id}")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<RefereeCategoryResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                       @UUID @PathVariable("id") String id) {
        log.info("Received get referee category by id request: {}", id);
        var category = getRefereeCategoryByIdUseCase.getById(id);
        var response = RefereeCategoryDomainToRefereeCategoryResponseConverter.convert(category);

        return ResponseEntity.ok(response);
    }
    @GetMapping
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<List<RefereeCategoryDto>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all referee categories request");
        var category = getAllRefereeCategoriesUseCase.getAll();

        return ResponseEntity.ok(category.stream()
                .map(RefereeCategoryDomainToRefereeCategoryDtoConverter::convert)
                .collect(Collectors.toList())
        );
    }
    @PutMapping("/{id}")
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public ResponseEntity<RefereeCategoryResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                          @UUID @PathVariable("id") String id,
                                                          @Valid @RequestBody UpdateRefereeCategoryInRequest request) {
        log.info("Received update referee request: {}", request);
        var category = UpdateRefereeCategoryInRequestToDomainConverter.convert(request);
        var updatedCategory = updateRefereeCategoryByIdUseCase.updateById(id, category);
        var response = RefereeCategoryDomainToRefereeCategoryResponseConverter.convert(updatedCategory);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequiresAuthority({UserRole.ADMIN, UserRole.SUPER_ADMIN})
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @UUID @PathVariable("id") String id) {
        log.info("Received delete referee category by id request: {}", id);
        deleteRefereeCategoryByIdUseCase.deleteById(id);
    }

}
