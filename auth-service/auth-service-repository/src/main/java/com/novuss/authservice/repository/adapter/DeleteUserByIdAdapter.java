package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.port.out.DeleteUserByIdPort;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUserByIdAdapter implements DeleteUserByIdPort {
    private final UserJpaRepository userJpaRepository;
    @Override
    public void deleteUserById(String id) {
        userJpaRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
        userJpaRepository.deleteById(UUID.fromString(id));
    }
}
