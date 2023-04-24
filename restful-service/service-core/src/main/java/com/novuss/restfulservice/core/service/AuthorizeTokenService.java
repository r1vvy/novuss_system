package com.novuss.restfulservice.core.service;

import com.novuss.restfulservice.core.port.in.token.AuthorizeTokenUseCase;
import com.novuss.restfulservice.core.port.out.AuthorizeUserPort;
import com.novuss.restfulservice.domain.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizeTokenService implements AuthorizeTokenUseCase {
    private final AuthorizeUserPort authorizeUserPort;
    @Override
    public String authorize(String token, List<UserRole> requiredAuthorities) {
        token = token.replace("Bearer ", "");
        return authorizeUserPort.authorize(token, requiredAuthorities);
    }
}
