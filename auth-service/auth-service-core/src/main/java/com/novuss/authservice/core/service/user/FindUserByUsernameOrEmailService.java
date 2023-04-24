package com.novuss.authservice.core.service.user;

import com.novuss.authservice.core.exception.EntityNotFoundException;
import com.novuss.authservice.core.port.in.user.FindUserByUsernameOrEmailUseCase;
import com.novuss.authservice.core.port.out.FindUserByEmailPort;
import com.novuss.authservice.core.port.out.FindUserByUsernamePort;
import com.novuss.authservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FindUserByUsernameOrEmailService implements FindUserByUsernameOrEmailUseCase {
    private final FindUserByUsernamePort findUserByUsernameOrEmailPort;
    private final FindUserByEmailPort findUserByEmailPort;

    @Override
    public User findUserByUsernameOrEmail(String username, String email) {
        var user = (username != null) ?
                findUserByUsernameOrEmailPort.findUserByUsername(username)
                        .orElseThrow(() -> new EntityNotFoundException("User not found with username = "+ username)) :
                findUserByEmailPort.findUserByEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException("User not found with email = "+ email));

        return user;
    }
}
