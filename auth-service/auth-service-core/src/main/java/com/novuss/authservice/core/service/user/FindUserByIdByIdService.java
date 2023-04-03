package com.novuss.authservice.core.service.user;

import com.novuss.authservice.core.port.in.user.FindUserByIdUseCase;
import com.novuss.authservice.core.port.out.FindUserByIdPort;
import com.novuss.authservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FindUserByIdByIdService implements FindUserByIdUseCase {
    private final FindUserByIdPort findUserByIdPort;

    @Override
    public User findUserById(String id) {
        var user = findUserByIdPort.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id = "+ id));

        return user;
    }
}
