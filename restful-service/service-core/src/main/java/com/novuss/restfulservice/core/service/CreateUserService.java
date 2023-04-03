package com.novuss.restfulservice.core.service;

import com.novuss.restfulservice.core.port.in.CreateUserUseCase;
import com.novuss.restfulservice.core.port.out.FindUserByUsernameAndEmailPort;
import com.novuss.restfulservice.core.port.out.SaveUserPort;
import com.restfulservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CreateUserService implements CreateUserUseCase {
    private final SaveUserPort saveUserPort;
    private final FindUserByUsernameAndEmailPort findUserByUsernameAndEmailPort;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User newUser) {
         findUserByUsernameAndEmailPort.findUserByUsernameAndEmail(newUser.username(), newUser.email())
                 .ifPresent(user -> {
                     throw new RuntimeException("User already exists");
                 });

         var encodedPassword = passwordEncoder.encode(newUser.password());
         newUser = newUser.toBuilder()
                 .password(encodedPassword)
                 .build();

         return saveUserPort.saveUser(newUser);
    }
}
