package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.port.out.UpdateUserByIdPort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.converter.MapStructMapper;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserByIdAdapter implements UpdateUserByIdPort {
    private final UserJpaRepository userJpaRepository;
    private final MapStructMapper mapStructMapper;
    @Override
    public User updateById(String id, User user) {
        var userId = UUID.fromString(id);
        var username = user.getUsername();
        var email = user.getEmail();


        var existingUserEntity = userJpaRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userJpaRepository.findByUsername(username)
                .ifPresent(userEntity -> {
                    if (!userEntity.getId().equals(existingUserEntity.getId())) {
                        log.warn("User with this username already exists: {}", username);
                        throw new RuntimeException("User with this username already exists");
                    }
                });
        userJpaRepository.findByEmail(email)
                .ifPresent(userEntity -> {
                    if (!userEntity.getId().equals(existingUserEntity.getId())) {
                        log.warn("User with this email already exists: {}", email);
                        throw new RuntimeException("User with this email already exists");
                    }
                });

        existingUserEntity.setUsername(username);
        existingUserEntity.setEmail(email);
        existingUserEntity.setPassword(user.getPassword());
        existingUserEntity.setRoles(user.getRoles());

        try {
            return mapStructMapper.userEntityToDomain(userJpaRepository.save(existingUserEntity));
        } catch(IllegalArgumentException | OptimisticLockingFailureException e) {
            log.warn("Failed to save user {}, due to: {}", user, e);
            throw new RuntimeException("Failed to save user: " + e.getMessage());
        }


    }
}
