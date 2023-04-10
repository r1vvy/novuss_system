package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.referee.*;
import com.novuss.restfulservice.in.converter.referee.CreateRefereeInRequestToDomainConverter;
import com.novuss.restfulservice.in.converter.referee.RefereeDomainToRefereeInResponseConverter;
import com.novuss.restfulservice.in.converter.referee.UpdateRefereeInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateRefereeInRequest;
import com.novuss.restfulservice.in.dto.request.UpdateRefereeInRequest;
import com.novuss.restfulservice.in.dto.response.RefereeInResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/referees")
@RequiredArgsConstructor
@Slf4j
public class RefereeController {
    private final SaveRefereeUseCase saveRefereeUseCase;
    private final GetRefereeByIdUseCase getRefereeByIdUseCase;
    private final GetAllRefereesUseCase getAllRefereesUseCase;
    private final UpdateRefereeByIdUseCase updateRefereeByIdUseCase;
    private final DeleteRefereeByIdUseCase deleteRefereeByIdUseCase;

    @PostMapping
    public ResponseEntity<RefereeInResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                    @RequestBody CreateRefereeInRequest request) {
        log.info("Received create referee request: {}", request);

        var referee = CreateRefereeInRequestToDomainConverter.convert(request);
        var createdReferee = saveRefereeUseCase.save(referee);
        var response = RefereeDomainToRefereeInResponseConverter.convert(createdReferee);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response)
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
    @GetMapping
    public ResponseEntity<RefereeInResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestParam("id") String id) {
        log.info("Received get referee by id request: {}", id);
        var referee = getRefereeByIdUseCase.getById(id);
        var response = RefereeDomainToRefereeInResponseConverter.convert(referee);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RefereeInResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all referees request");
        var referee = getAllRefereesUseCase.getAll();

        return ResponseEntity.ok(referee.stream()
                .map(RefereeDomainToRefereeInResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }
    @PutMapping
    public ResponseEntity<RefereeInResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                    @RequestParam("id") String id,
                                                    @RequestBody UpdateRefereeInRequest request) {
        log.info("Received update referee request: {}", request);
        var referee = UpdateRefereeInRequestToDomainConverter.convert(request);
        var updatedReferee = updateRefereeByIdUseCase.updateById(id, referee);
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
