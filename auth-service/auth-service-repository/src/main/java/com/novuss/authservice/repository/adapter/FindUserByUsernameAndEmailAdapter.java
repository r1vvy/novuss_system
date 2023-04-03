package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.port.out.FindUserByUsernameAndEmailPort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.converter.MapStructMapper;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindUserByUsernameAndEmailAdapter implements FindUserByUsernameAndEmailPort {
    private final UserJpaRepository userJpaRepository;
    private final MapStructMapper mapper;

    @Override
    public Optional<User> findUserByUsernameAndEmail(String username, String email) {
        return userJpaRepository.findByUsernameAndEmail(username, email)
                .map(mapper::userEntityToDomain);
    }
}
