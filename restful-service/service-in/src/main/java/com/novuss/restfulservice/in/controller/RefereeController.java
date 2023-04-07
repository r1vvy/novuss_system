package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.referee.*;
import com.novuss.restfulservice.in.converter.*;
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
        var response = com.novuss.restfulservice.in.converter.RefereeDomainToRefereeInResponseConverter.convert(createdReferee);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response)
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefereeInResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                 @PathVariable("id") String id) {
        log.info("Received get referee request: {}", id);
        var referee = getRefereeByIdUseCase.getById(id);
        var response = com.novuss.restfulservice.in.converter.RefereeDomainToRefereeInResponseConverter.convert(referee);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RefereeInResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all referees request");
        var referee = getAllRefereesUseCase.getAll();

        return ResponseEntity.ok(referee.stream()
                .map(com.novuss.restfulservice.in.converter.RefereeDomainToRefereeInResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefereeInResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                    @PathVariable("id") String id,
                                                    @RequestBody UpdateRefereeInRequest request) {
        log.info("Received update referee request: {}", request);
        var referee = UpdateRefereeInRequestToDomainConverter.convert(request);
        var updatedReferee = updateRefereeByIdUseCase.updateById(referee, id);
        var response = RefereeDomainToRefereeInResponseConverter.convert(updatedReferee);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @PathVariable("id") String id) {
        log.info("Received delete referee request: {}", id);
        deleteRefereeByIdUseCase.deleteById(id);
    }
}
