package com.novuss.authservice.in.controller;

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
import org.springframework.http.HttpHeaders;
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
    private final ExtendTokenExpiryUseCase extendTokenExpiryUseCase;

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
        var extendedToken = extendTokenExpiryUseCase.extendTokenExpiry(authorizationHeader);
        log.debug("Extended token: {}", extendedToken);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(response.id())
                .toUri();

        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + extendedToken);
        headers.setLocation(location);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
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
        var user = findUserByIdUseCase.findById(id);
        var extendedToken = extendTokenExpiryUseCase.extendTokenExpiry(authorizationHeader);

        var responseDto = UserDomainToGetUserInResponseConverter.convert(user);
        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + extendedToken);

        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetUserInResponse>> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("Received get all users request");
        authorizeRequestByTokenService.authorizeByRequiredAuthorities(
                authorizationHeader,
                List.of(UserRole.ADMIN)
        );

        var users = getAllUsersUseCase.getAllUsers();
        var extendedToken = extendTokenExpiryUseCase.extendTokenExpiry(authorizationHeader);

        var responseDto = users.stream()
                .map(UserDomainToGetUserInResponseConverter::convert)
                .collect(Collectors.toList());
        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + extendedToken);

        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
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
        var responseDto = UserDomainToGetUserInResponseConverter.convert(updatedUser);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestParam("id") String id) {
        log.info("Received delete person request: {}", id);
        try {
            authorizeRequestByTokenService.authorizeByRequiredAuthorities(authorizationHeader, List.of(UserRole.ADMIN));
        } catch (AccessDeniedException exc) {
            authorizeRequestByTokenService.authorizeByUserId(authorizationHeader, id);
        }

        deleteUserByIdUseCase.deleteUserById(id);

        var extendedToken = extendTokenExpiryUseCase.extendTokenExpiry(authorizationHeader);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + extendedToken);

        return ResponseEntity.noContent().headers(headers).build();
    }

}
