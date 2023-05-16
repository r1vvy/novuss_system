package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.referee.*;
import com.novuss.restfulservice.in.util.converter.referee.CreateRefereeInRequestToDomainConverter;
import com.novuss.restfulservice.in.util.converter.referee.RefereeDomainToRefereeInResponseConverter;
import com.novuss.restfulservice.in.util.converter.referee.UpdateRefereeInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateRefereeInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateRefereeInRequest;
import com.novuss.restfulservice.in.dto.response.RefereeResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/referees")
@RequiredArgsConstructor
@Slf4j
@Validated
public class RefereeController {
    private final SaveRefereeUseCase saveRefereeUseCase;
    private final GetRefereeByIdUseCase getRefereeByIdUseCase;
    private final GetAllRefereesUseCase getAllRefereesUseCase;
    private final UpdateRefereeByIdUseCase updateRefereeByIdUseCase;
    private final DeleteRefereeByIdUseCase deleteRefereeByIdUseCase;
    private final UpdateRefereeCategoryForRefereeUseCase updateRefereeCategoryForRefereeUseCase;

    @PostMapping
    public ResponseEntity<RefereeResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                  @RequestBody CreateRefereeInRequest request) {
        log.info("Received create referee request: {}", request);

        var referee = CreateRefereeInRequestToDomainConverter.convert(request);
        var createdReferee = saveRefereeUseCase.save(referee);
        var response = RefereeDomainToRefereeInResponseConverter.convert(createdReferee);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RefereeResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                               @PathVariable("id") String id) {
        log.info("Received get referee by id request: {}", id);
        var referee = getRefereeByIdUseCase.getById(id);
        var response = RefereeDomainToRefereeInResponseConverter.convert(referee);

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<Page<RefereeResponse>> getAll(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @Min(value = 0, message = "Minimum page value is 0")
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @Range(min = MIN_PAGE_SIZE, max = MAX_PAGE_SIZE, message = "Page size must be between " + MIN_PAGE_SIZE + " and " + MAX_PAGE_SIZE)
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size
    ) {
        log.info("Received get all referees request");
        var pageable = PageRequest.of(page, size);
        var referees = getAllRefereesUseCase.getAllByPage(pageable);

        var response = referees.map(RefereeDomainToRefereeInResponseConverter::convert);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RefereeResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable("id") String id,
                                                  @RequestBody UpdateRefereeInRequest request) {
        log.info("Received update referee request: {}", request);
        var referee = UpdateRefereeInRequestToDomainConverter.convert(request);
        var updatedReferee = updateRefereeByIdUseCase.updateById(id, referee);
        var response = RefereeDomainToRefereeInResponseConverter.convert(updatedReferee);


        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}/category")
    public ResponseEntity<RefereeResponse> updateRefereeCategory(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                                                 @PathVariable("id") String id,
                                                                 @RequestParam(value = "id", required = false) String refereeCategoryId) {
        log.info("Received update referee category for referee request: {}", id);
        var updatedReferee = updateRefereeCategoryForRefereeUseCase.updateRefereeCategoryForReferee(id, refereeCategoryId);
        var response = RefereeDomainToRefereeInResponseConverter.convert(updatedReferee);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete referee by id request: {}", id);
        deleteRefereeByIdUseCase.deleteById(id);
    }

}
