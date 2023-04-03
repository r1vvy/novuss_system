package com.novuss.authservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.novuss")
public class AuthorizationApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApp.class);
    }
}
