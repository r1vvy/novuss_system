package com.novuss.authservice.in;

import com.novuss.authservice.in.converter.CreateUserInRequestToUserDomainConverter;
import com.novuss.authservice.in.converter.UserDomainToGetUserInResponseConverter;
import com.novuss.authservice.core.port.in.user.CreateUserUseCase;
import com.novuss.authservice.core.port.in.user.FindUserByIdUseCase;
import com.novuss.authservice.in.dto.request.CreateUserInRequest;
import com.novuss.authservice.in.dto.response.GetUserInResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;

    @PostMapping
    public ResponseEntity<GetUserInResponse> createUser(@RequestBody @Valid CreateUserInRequest request) {
        log.debug("Recieved create user request: {}", request.username());

        var user = createUserUseCase.createUser(CreateUserInRequestToUserDomainConverter.convert(request));
        var response = UserDomainToGetUserInResponseConverter.convert(user);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserInResponse> getUser(@PathVariable String id) {
        log.debug("Recieved get user request: {}", id);

        var user = findUserByIdUseCase.findUserById(id);
        var response = UserDomainToGetUserInResponseConverter.convert(user);

        return ResponseEntity.ok().body(response);
    }
}
