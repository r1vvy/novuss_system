package com.novuss.authservice.core.service.user;

import com.novuss.authservice.core.port.in.user.UpdateUserByIdUseCase;
import com.novuss.authservice.core.port.out.UpdateUserByIdPort;
import com.novuss.authservice.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserByIdService implements UpdateUserByIdUseCase {
    private final UpdateUserByIdPort updateUserByIdPort;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User updateUserById(String id, User user) {
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user = user.toBuilder()
                .password(encodedPassword)
                .build();

        return updateUserByIdPort.updateById(id, user);
    }
}
