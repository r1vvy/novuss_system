package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.exception.EntityExistsException;
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
        log.info("Updating user with id: {}", id);
        var userId = UUID.fromString(id);
        var username = user.getUsername();
        var email = user.getEmail();


        var existingUserEntity = userJpaRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userJpaRepository.findByUsername(username)
                .ifPresent(userEntity -> {
                    if (!userEntity.getId().equals(existingUserEntity.getId())) {
                        log.warn("User with this username already exists: {}", username);
                        throw new EntityExistsException("User with this username already exists");
                    }
                });
        userJpaRepository.findByEmail(email)
                .ifPresent(userEntity -> {
                    if (!userEntity.getId().equals(existingUserEntity.getId())) {
                        log.warn("User with this email already exists: {}", email);
                        throw new EntityExistsException("User with this email already exists");
                    }
                });

        user.setId(id);
        user.setCreatedAt(existingUserEntity.getCreatedAt());
        user.setRoles(existingUserEntity.getRoles());
        log.debug("Updated user: {}", user);

        var updatedUserEntity = mapStructMapper.userDomainToEntity(user);
        var saveUserEntity = userJpaRepository.save(updatedUserEntity);
        log.info("Successfully updated user with id: {}", id);
        log.debug("Updated user entity: {}", saveUserEntity);

        try {
            return mapStructMapper.userEntityToDomain(saveUserEntity);
        } catch(IllegalArgumentException | OptimisticLockingFailureException e) {
            log.warn("Failed to save user {}, due to: {}", user, e);
            throw new RuntimeException("Failed to save user: " + e.getMessage());
        }


    }
}
