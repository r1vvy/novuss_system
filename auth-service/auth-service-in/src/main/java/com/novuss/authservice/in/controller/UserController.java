package com.novuss.authservice.in.controller;

import com.novuss.authservice.core.port.in.token.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.port.in.user.*;
import com.novuss.authservice.core.service.AuthorizeRequestByTokenService;
import com.novuss.authservice.domain.UserRole;
import com.novuss.authservice.in.dto.request.CreateUserInRequest;
import com.novuss.authservice.in.dto.request.UpdateUserInRequest;
import com.novuss.authservice.in.dto.response.GetUserInResponse;
import com.novuss.authservice.in.util.RequiresAuthority;
import com.novuss.authservice.in.util.converter.CreateUserInRequestToUserDomainConverter;
import com.novuss.authservice.in.util.converter.UpdateUserInRequestToDomainConverter;
import com.novuss.authservice.in.util.converter.UserDomainToGetUserInResponseConverter;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/users")
public class UserController {
    private final SaveUserUseCase saveUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final UpdateUserByIdUseCase updateUserByIdUseCase;
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;
    private final AuthorizeRequestByTokenService authorizeRequestByTokenService;

    @PostMapping
    @Validated
    public ResponseEntity<GetUserInResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                                    @Valid @RequestBody CreateUserInRequest request) {
        log.debug("Received create user request: {}", request);
        authorizeRequestByTokenService.authorizeByRequiredAuthorities(
                authorizationHeader,
                List.of(UserRole.ADMIN)
        );

        var user = CreateUserInRequestToUserDomainConverter.convert(request);
        var createdUser = saveUserUseCase.save(user);
        var response = UserDomainToGetUserInResponseConverter.convert(createdUser);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<GetUserInResponse> get(@RequestHeader("Authorization") String authorizationHeader,
                                                @RequestParam("id") String id) {
        log.info("Received get user by id request: {}", id);

        try {
            authorizeRequestByTokenService.authorizeByRequiredAuthorities(authorizationHeader, List.of(UserRole.ADMIN));
        } catch (AccessDeniedException exc) {
            authorizeRequestByTokenService.authorizeByUserId(authorizationHeader, id);
        }

        var person = findUserByIdUseCase.findById(id);
        var response = UserDomainToGetUserInResponseConverter.convert(person);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @RequiresAuthority(UserRole.ADMIN)
    public ResponseEntity<List<GetUserInResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all users request");
        authorizeRequestByTokenService.authorizeByRequiredAuthorities(
                authorizationHeader,
                List.of(UserRole.ADMIN)
        );

        var users = getAllUsersUseCase.getAllUsers();

        return ResponseEntity.ok(users.stream()
                .map(UserDomainToGetUserInResponseConverter::convert)
                .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<GetUserInResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestParam("id") String id,
                                                   @Valid @RequestBody UpdateUserInRequest request) {
        log.info("Received update user request: {}", request);
        try {
            authorizeRequestByTokenService.authorizeByRequiredAuthorities(authorizationHeader, List.of(UserRole.ADMIN));
        } catch (AccessDeniedException exc) {
            authorizeRequestByTokenService.authorizeByUserId(authorizationHeader, id);
        }

        var user = UpdateUserInRequestToDomainConverter.convert(request);
        var updatedUser = updateUserByIdUseCase.updateUserById(id, user);
        var response = UserDomainToGetUserInResponseConverter.convert(updatedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("Authorization") String authorizationHeader,
                       @RequestParam("id") String id) {
        log.info("Received delete person request: {}", id);
        try {
            authorizeRequestByTokenService.authorizeByRequiredAuthorities(authorizationHeader, List.of(UserRole.ADMIN));
        } catch (AccessDeniedException exc) {
            authorizeRequestByTokenService.authorizeByUserId(authorizationHeader, id);
        }

        deleteUserByIdUseCase.deleteUserById(id);
    }
}
