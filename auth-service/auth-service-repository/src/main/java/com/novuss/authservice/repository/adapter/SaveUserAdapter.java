package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.exception.EntityExistsException;
import com.novuss.authservice.core.port.out.SaveUserPort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.converter.MapStructMapper;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class SaveUserAdapter implements SaveUserPort {
    private final UserJpaRepository userJpaRepository;
    private final MapStructMapper mapper;

    @Override
    public User saveUser(User user) {
        userJpaRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new EntityExistsException("User with this username already exists");
                });
        userJpaRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new EntityExistsException("User with this email already exists");
                });
        try {
            var savedUserEntity = userJpaRepository.save(mapper.userDomainToEntity(user));

            return mapper.userEntityToDomain(savedUserEntity);
        } catch(IllegalArgumentException | OptimisticLockingFailureException e) {
            log.warn("Failed to save user {}, due to: {}", user, e);
            throw new RuntimeException("Failed to save user: " + e.getMessage());
        }
    }
}
