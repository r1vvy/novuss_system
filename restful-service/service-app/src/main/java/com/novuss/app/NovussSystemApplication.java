package com.novuss.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.novuss")
@EnableJpaRepositories(basePackages = "com.novuss.repository")
@EntityScan(basePackages = "com.novuss.repository")
public class NovussSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(NovussSystemApplication.class);
    }
}
