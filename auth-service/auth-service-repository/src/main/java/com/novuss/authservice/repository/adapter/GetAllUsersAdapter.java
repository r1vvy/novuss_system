package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.port.out.GetAllUsersPort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.converter.MapStructMapper;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllUsersAdapter implements GetAllUsersPort {
    private final UserJpaRepository userJpaRepository;
    private final MapStructMapper mapStructMapper;
    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.of(
                userJpaRepository.findAll()
                .stream()
                .map(mapStructMapper::userEntityToDomain)
                .collect(Collectors.toList())
        );
    }
}
