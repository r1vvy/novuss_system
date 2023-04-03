package com.novuss.restfulservice.in.controller;

import com.novuss.restfulservice.core.port.in.CreateUserUseCase;
import com.novuss.restfulservice.core.port.in.FindUserUseCase;
import com.novuss.restfulservice.in.converter.CreateUserInRequestToUserDomainConverter;
import com.novuss.restfulservice.in.converter.UserDomainToGetUserInResponseConverter;
import com.novuss.restfulservice.in.dto.GetUserInResponse;
import com.novuss.restfulservice.in.dto.request.CreateUserInRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;

    @PostMapping("")
    public ResponseEntity<GetUserInResponse> createUser(@RequestBody @Valid CreateUserInRequest request) {
        log.debug("Recieved create user request: {}", request.username());

        var user = createUserUseCase.createUser(CreateUserInRequestToUserDomainConverter.convert(request));
        var response = UserDomainToGetUserInResponseConverter.convert(user);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserInResponse> getUser(@PathVariable String id) {
        log.debug("Recieved get user request: {}", id);

        var user = findUserUseCase.findUser(id);
        var response = UserDomainToGetUserInResponseConverter.convert(user);

        return ResponseEntity.ok().body(response);
    }
}
