package com.novuss.restfulservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.novuss")
public class RestfulServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestfulServiceApplication.class);
    }
}
