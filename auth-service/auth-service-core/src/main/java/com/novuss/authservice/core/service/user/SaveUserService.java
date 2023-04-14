package com.novuss.authservice.core.service.user;

import com.novuss.authservice.core.port.in.user.SaveUserUseCase;
import com.novuss.authservice.core.port.out.FindUserByUsernameAndEmailPort;
import com.novuss.authservice.core.port.out.SaveUserPort;
import com.novuss.authservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SaveUserService implements SaveUserUseCase {
    private final SaveUserPort saveUserPort;
    private final FindUserByUsernameAndEmailPort findUserByUsernameAndEmailPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User newUser) {
         findUserByUsernameAndEmailPort.findUserByUsernameAndEmail(newUser.getUsername(), newUser.getEmail())
                 .ifPresent(user -> {
                     throw new RuntimeException("User already exists");
                 });

         var encodedPassword = passwordEncoder.encode(newUser.getPassword());
         newUser = newUser.toBuilder()
                 .password(encodedPassword)
                 .build();

         return saveUserPort.saveUser(newUser);
    }
}
