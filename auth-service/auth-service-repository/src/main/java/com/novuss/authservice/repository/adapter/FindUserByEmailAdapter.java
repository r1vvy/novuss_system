package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.port.out.FindUserByEmailPort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.converter.MapStructMapper;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindUserByEmailAdapter implements FindUserByEmailPort {
    private final UserJpaRepository userJpaRepository;
    private final MapStructMapper mapper;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(mapper::userEntityToDomain);
    }
}
