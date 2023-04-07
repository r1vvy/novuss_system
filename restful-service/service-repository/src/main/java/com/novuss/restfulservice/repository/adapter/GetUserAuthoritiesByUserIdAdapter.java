package com.novuss.restfulservice.repository.adapter;

import com.novuss.restfulservice.core.port.out.GetUserAuthoritiesByUserIdPort;
import com.novuss.restfulservice.repository.repository.jpa.UserJpaRepository;
import com.restfulservice.domain.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetUserAuthoritiesByUserIdAdapter implements GetUserAuthoritiesByUserIdPort {
    private final UserJpaRepository userJpaRepository;

    @Override
    public List<UserRole> getUserAuthoritiesByUserId(String id) {
        var userEntity = userJpaRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userEntity.getRoles()
                .stream()
                .toList();
    }
}
