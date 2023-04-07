package com.novuss.restfulservice.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.novuss")
@EntityScan(basePackages = "com.novuss")
public class DatabaseConfig {
}
