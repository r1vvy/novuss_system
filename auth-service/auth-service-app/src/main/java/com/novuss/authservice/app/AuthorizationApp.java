package com.novuss.authservice.app;

import com.novuss.authservice.core.port.in.user.SaveUserUseCase;
import com.novuss.authservice.core.port.out.FindUserByUsernamePort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.domain.UserRole;
import com.novuss.authservice.in.dto.request.CreateUserInRequest;
import com.novuss.authservice.repository.entity.UserEntity;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;


// TODO: On production remove default user.
@SpringBootApplication(scanBasePackages = "com.novuss")
@RequiredArgsConstructor
public class AuthorizationApp implements CommandLineRunner {
    private final SaveUserUseCase saveUserUseCase;
    private final FindUserByUsernamePort findUserByUsernamePort;
    public static void main(String[] args) {
        // make a default user

        SpringApplication.run(AuthorizationApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!findUserByUsernamePort.findUserByUsername("dev").isPresent()) {
            var devUser = User.builder()
                    .username("dev")
                    .email("dev@dev.com")
                    .password("dev123")
                    .roles(Set.of(UserRole.USER, UserRole.ADMIN, UserRole.SUPER_ADMIN))
                    .build();

            saveUserUseCase.save(devUser);
        }
    }
}
