package com.novuss.restfulservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.novuss.restfulservice")
@EnableJpaRepositories(basePackages = "com.novuss.restfulservice.repository")
@EntityScan(basePackages = "com.novuss.restfulservice.repository")
public class RestfulServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestfulServiceApplication.class);
    }
}
