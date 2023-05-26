package com.novuss.authservice.in.controller;

import com.novuss.authservice.core.port.in.token.AuthorizeRequestByTokenUseCase;
import com.novuss.authservice.core.port.in.token.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.port.in.user.*;
import com.novuss.authservice.core.service.AuthorizeRequestByTokenService;
import com.novuss.authservice.domain.UserRole;
import com.novuss.authservice.in.dto.request.CreateUserInRequest;
import com.novuss.authservice.in.dto.request.UpdateUserInRequest;
import com.novuss.authservice.in.dto.response.GetUserInResponse;
import com.novuss.authservice.in.util.converter.CreateUserInRequestToUserDomainConverter;
import com.novuss.authservice.in.util.converter.UpdateUserInRequestToDomainConverter;
import com.novuss.authservice.in.util.converter.UserDomainToGetUserInResponseConverter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@Validated
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final SaveUserUseCase saveUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final UpdateUserByIdUseCase updateUserByIdUseCase;
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;
    private final AuthorizeRequestByTokenUseCase authorizeRequestByTokenUseCase;

    @PostMapping
    @Validated
    public ResponseEntity<GetUserInResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                    @Valid @RequestBody CreateUserInRequest request) {
        log.debug("Received create user request: {}", request);
        authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(
                authorizationHeader,
                List.of(UserRole.ADMIN)
        );

        var user = CreateUserInRequestToUserDomainConverter.convert(request);
        var createdUser = saveUserUseCase.save(user);
        var response = UserDomainToGetUserInResponseConverter.convert(createdUser);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        var headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserInResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                 @UUID @PathVariable("id") String id) {
        log.info("Received get user by id request: {}", id);
        authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(
                authorizationHeader,
                List.of(UserRole.ADMIN)
        );

        try {
            authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(authorizationHeader, List.of(UserRole.ADMIN));
        } catch (AccessDeniedException exc) {
            authorizeRequestByTokenUseCase.authorizeByUserId(authorizationHeader, id);
        }
        var user = findUserByIdUseCase.findById(id);
        var responseDto = UserDomainToGetUserInResponseConverter.convert(user);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetUserInResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all users request");
        authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(
                authorizationHeader,
                List.of(UserRole.ADMIN)
        );

        var users = getAllUsersUseCase.getAllUsers();
        var responseDto = users.stream()
                .map(UserDomainToGetUserInResponseConverter::convert)
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUserInResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                   @UUID @PathVariable("id") String id,
                                                   @Valid @RequestBody UpdateUserInRequest request) {
        log.info("Received update user request: {}", request);
        try {
            authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(authorizationHeader, List.of(UserRole.ADMIN));
        } catch (AccessDeniedException exc) {
            authorizeRequestByTokenUseCase.authorizeByUserId(authorizationHeader, id);
        }

        var user = UpdateUserInRequestToDomainConverter.convert(request);
        var updatedUser = updateUserByIdUseCase.updateUserById(id, user);
        var responseDto = UserDomainToGetUserInResponseConverter.convert(updatedUser);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                      @UUID @PathVariable("id") String id) {
        log.info("Received delete person request: {}", id);
        try {
            authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(authorizationHeader, List.of(UserRole.ADMIN));
        } catch (AccessDeniedException exc) {
            authorizeRequestByTokenUseCase.authorizeByUserId(authorizationHeader, id);
        }
        deleteUserByIdUseCase.deleteUserById(id);


        return ResponseEntity.noContent().build();
    }

}
