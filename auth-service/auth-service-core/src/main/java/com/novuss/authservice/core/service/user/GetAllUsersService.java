package com.novuss.authservice.core.service.user;

import com.novuss.authservice.core.port.in.user.GetAllUsersUseCase;
import com.novuss.authservice.core.port.out.GetAllUsersPort;
import com.novuss.authservice.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllUsersService implements GetAllUsersUseCase {
    private final GetAllUsersPort getAllUsersPort;

    @Override
    public List<User> getAllUsers() {
        return getAllUsersPort.getAllUsers()
                .orElseThrow(
                        () -> new RuntimeException("No users found")
                );
    }
}
