package com.novuss.restfulservice.core.service;

import com.novuss.restfulservice.core.port.in.GetUserAuthoritiesByUserIdUseCase;
import com.novuss.restfulservice.core.port.out.GetUserAuthoritiesByUserIdPort;
import com.novuss.restfulservice.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GetUserAuthoritiesByUserAuthoritiesByUserIdService implements GetUserAuthoritiesByUserIdUseCase {
    private final GetUserAuthoritiesByUserIdPort getUserAuthoritiesByUserIdPort;

    @Override
    public List<UserRole> getUserAuthorityByUserId(String id) {
        var userAuthorities = getUserAuthoritiesByUserIdPort.getUserAuthoritiesByUserId(id);

        return userAuthorities;
    }
}
