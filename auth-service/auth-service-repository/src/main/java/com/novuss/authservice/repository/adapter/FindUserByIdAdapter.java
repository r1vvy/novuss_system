package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.port.out.FindUserByIdPort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.converter.MapStructMapper;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class FindUserByIdAdapter implements FindUserByIdPort {
    private final UserJpaRepository userJpaRepository;
    private final MapStructMapper mapper;

    @Override
    public Optional<User> findUserById(String id) {

        return userJpaRepository.findById(UUID.fromString(id))
                .map(mapper::userEntityToDomain);
    }
}
